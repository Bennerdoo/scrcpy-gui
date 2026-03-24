package com.scrcpy.gui.core;

import com.scrcpy.gui.util.ScrcpyResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Executes adb commands safe and asynchronously.
 */
public class AdbRunner {
    
    public static class CommandResult {
        public final int exitCode;
        public final String stdout;
        public final String stderr;

        public CommandResult(int exitCode, String stdout, String stderr) {
            this.exitCode = exitCode;
            this.stdout = stdout;
            this.stderr = stderr;
        }

        public boolean isSuccess() {
            return exitCode == 0;
        }
    }

    /**
     * Executes an adb command asynchronously.
     */
    public void executeAsync(List<String> args, Consumer<CommandResult> callback) {
        new Thread(() -> {
            String adb = ScrcpyResourceManager.getAdbExecutable();
            List<String> command = new ArrayList<>();
            command.add(adb);
            command.addAll(args);

            try {
                ProcessBuilder builder = new ProcessBuilder(command);
                Process process = builder.start();

                StringBuilder stdout = new StringBuilder();
                StringBuilder stderr = new StringBuilder();

                Thread outThread = new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stdout.append(line).append("\n");
                        }
                    } catch (IOException e) {
                        // Ignore
                    }
                });

                Thread errThread = new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stderr.append(line).append("\n");
                        }
                    } catch (IOException e) {
                        // Ignore
                    }
                });

                outThread.start();
                errThread.start();

                int exitCode = process.waitFor();
                outThread.join();
                errThread.join();

                if (callback != null) {
                    callback.accept(new CommandResult(exitCode, stdout.toString(), stderr.toString()));
                }

            } catch (IOException | InterruptedException e) {
                if (callback != null) {
                    callback.accept(new CommandResult(-1, "", "Failed to execute: " + e.getMessage()));
                }
            }
        }).start();
    }

    /**
     * Run 'adb pair ip:port code'
     */
    public void pair(String ipPort, String code, Consumer<CommandResult> callback) {
        List<String> args = new ArrayList<>();
        args.add("pair");
        args.add(ipPort);
        args.add(code);
        executeAsync(args, callback);
    }

    /**
     * Run 'adb connect ip:port'
     */
    public void connect(String ipPort, Consumer<CommandResult> callback) {
        List<String> args = new ArrayList<>();
        args.add("connect");
        args.add(ipPort);
        executeAsync(args, callback);
    }

    /**
     * Check wireless services found via mdns
     */
    public void checkMdns(Consumer<CommandResult> callback) {
        List<String> args = new ArrayList<>();
        args.add("mdns");
        args.add("services");
        executeAsync(args, callback);
    }
}
