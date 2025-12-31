package com.scrcpy.gui.core;

import java.io.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Executes scrcpy process and manages I/O
 */
public class ProcessExecutor {
    private Process process;
    private Thread outputThread;
    private Thread errorThread;
    private Consumer<String> outputCallback;
    private Consumer<String> errorCallback;
    private volatile boolean running = false;

    public ProcessExecutor(Consumer<String> outputCallback, Consumer<String> errorCallback) {
        this.outputCallback = outputCallback;
        this.errorCallback = errorCallback;
    }

    /**
     * Start scrcpy process with the given command
     */
    public void start(List<String> command) throws IOException {
        if (running) {
            throw new IllegalStateException("Process is already running");
        }

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(false);
        
        process = builder.start();
        running = true;

        // Start output reader thread
        outputThread = new Thread(() -> readStream(process.getInputStream(), outputCallback));
        outputThread.setDaemon(true);
        outputThread.start();

        // Start error reader thread
        errorThread = new Thread(() -> readStream(process.getErrorStream(), errorCallback));
        errorThread.setDaemon(true);
        errorThread.start();

        // Monitor process termination
        new Thread(() -> {
            try {
                int exitCode = process.waitFor();
                running = false;
                if (outputCallback != null) {
                    outputCallback.accept("\n[Process exited with code: " + exitCode + "]\n");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * Stop the running process
     */
    public void stop() {
        if (process != null && running) {
            process.destroy();
            running = false;
            
            // Give it a moment to terminate gracefully
            try {
                Thread.sleep(1000);
                if (process.isAlive()) {
                    process.destroyForcibly();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                process.destroyForcibly();
            }
        }
    }

    /**
     * Check if process is running
     */
    public boolean isRunning() {
        return running && process != null && process.isAlive();
    }

    /**
     * Get the process exit code (only valid after process terminates)
     */
    public int getExitCode() {
        if (process == null) {
            return -1;
        }
        try {
            return process.exitValue();
        } catch (IllegalThreadStateException e) {
            return -1; // Process still running
        }
    }

    private void readStream(InputStream stream, Consumer<String> callback) {
        if (callback == null) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                callback.accept(line + "\n");
            }
        } catch (IOException e) {
            if (running) {
                callback.accept("[Error reading stream: " + e.getMessage() + "]\n");
            }
        }
    }
}
