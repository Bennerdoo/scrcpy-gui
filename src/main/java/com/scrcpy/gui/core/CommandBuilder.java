package com.scrcpy.gui.core;

import com.scrcpy.gui.config.ScrcpyConfig;
import com.scrcpy.gui.util.ScrcpyResourceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds scrcpy command from configuration
 */
public class CommandBuilder {
    private final ScrcpyConfig config;

    public CommandBuilder(ScrcpyConfig config) {
        this.config = config;
    }

    /**
     * Build complete scrcpy command
     */
    public List<String> buildCommand() {
        List<String> command = new ArrayList<>();
        command.add(ScrcpyResourceManager.getScrcpyExecutable());

        // Connection options
        addOption(command, "--serial", config.getSerial());
        addFlag(command, "-d", config.isSelectUsb());
        addFlag(command, "-e", config.isSelectTcpip());
        addOption(command, "--tcpip", config.getTcpipAddress());
        addOption(command, "--port", config.getPortRange());
        addFlag(command, "--force-adb-forward", config.isForceAdbForward());
        addOption(command, "--tunnel-host", config.getTunnelHost());
        addOption(command, "--tunnel-port", config.getTunnelPort());

        // Video options
        if (!config.getVideoSource().equals("display")) {
            addOption(command, "--video-source", config.getVideoSource());
        }
        if (!config.getVideoCodec().equals("h264")) {
            addOption(command, "--video-codec", config.getVideoCodec());
        }
        addOption(command, "--video-encoder", config.getVideoEncoder());
        if (!config.getVideoBitRate().equals("8M")) {
            addOption(command, "--video-bit-rate", config.getVideoBitRate());
        }
        addOption(command, "--max-size", config.getMaxSize());
        addOption(command, "--max-fps", config.getMaxFps());
        addOption(command, "--video-buffer", config.getVideoBuffer());
        addOption(command, "--video-codec-options", config.getVideoCodecOptions());
        addFlag(command, "--no-video", config.isNoVideo());
        addFlag(command, "--no-video-playback", config.isNoVideoPlayback());
        addFlag(command, "--no-mipmaps", config.isNoMipmaps());
        addFlag(command, "--no-downsize-on-error", config.isNoDownsizeOnError());

        // Audio options
        if (!config.getAudioSource().equals("output")) {
            addOption(command, "--audio-source", config.getAudioSource());
        }
        if (!config.getAudioCodec().equals("opus")) {
            addOption(command, "--audio-codec", config.getAudioCodec());
        }
        addOption(command, "--audio-encoder", config.getAudioEncoder());
        if (!config.getAudioBitRate().equals("128K")) {
            addOption(command, "--audio-bit-rate", config.getAudioBitRate());
        }
        if (!config.getAudioBuffer().equals("50")) {
            addOption(command, "--audio-buffer", config.getAudioBuffer());
        }
        if (!config.getAudioOutputBuffer().equals("5")) {
            addOption(command, "--audio-output-buffer", config.getAudioOutputBuffer());
        }
        addOption(command, "--audio-codec-options", config.getAudioCodecOptions());
        addFlag(command, "--audio-dup", config.isAudioDup());
        addFlag(command, "--no-audio", config.isNoAudio());
        addFlag(command, "--no-audio-playback", config.isNoAudioPlayback());
        addFlag(command, "--require-audio", config.isRequireAudio());

        // Control options
        addFlag(command, "--no-control", config.isNoControl());
        addFlag(command, "--no-playback", config.isNoPlayback());
        addFlag(command, "--no-clipboard-autosync", config.isNoClipboardAutosync());
        addFlag(command, "--legacy-paste", config.isLegacyPaste());
        addFlag(command, "--prefer-text", config.isPreferText());
        addFlag(command, "--raw-key-events", config.isRawKeyEvents());
        addFlag(command, "--no-key-repeat", config.isNoKeyRepeat());
        addFlag(command, "--no-mouse-hover", config.isNoMouseHover());

        // Keyboard/Mouse/Gamepad options
        addOption(command, "--keyboard", config.getKeyboardMode());
        addOption(command, "--mouse", config.getMouseMode());
        addOption(command, "--mouse-bind", config.getMouseBindings());
        addOption(command, "--gamepad", config.getGamepadMode());
        addFlag(command, "--otg", config.isOtgMode());

        // Device options
        addOption(command, "--display-id", config.getDisplayId());
        addFlag(command, "--show-touches", config.isShowTouches());
        addFlag(command, "--stay-awake", config.isStayAwake());
        addFlag(command, "--turn-screen-off", config.isTurnScreenOff());
        addOption(command, "--screen-off-timeout", config.getScreenOffTimeout());
        addFlag(command, "--power-off-on-close", config.isPowerOffOnClose());
        addFlag(command, "--no-power-on", config.isNoPowerOn());
        addFlag(command, "--disable-screensaver", config.isDisableScreensaver());

        // Window options
        addOption(command, "--window-title", config.getWindowTitle());
        addOption(command, "--window-x", config.getWindowX());
        addOption(command, "--window-y", config.getWindowY());
        addOption(command, "--window-width", config.getWindowWidth());
        addOption(command, "--window-height", config.getWindowHeight());
        addFlag(command, "--window-borderless", config.isWindowBorderless());
        addFlag(command, "--always-on-top", config.isAlwaysOnTop());
        addFlag(command, "--fullscreen", config.isFullscreen());
        addFlag(command, "--no-window", config.isNoWindow());

        // Recording options
        addOption(command, "--record", config.getRecordFile());
        addOption(command, "--record-format", config.getRecordFormat());
        if (!config.getRecordOrientation().equals("0")) {
            addOption(command, "--record-orientation", config.getRecordOrientation());
        }
        addOption(command, "--time-limit", config.getTimeLimit());

        // Camera options
        addOption(command, "--camera-id", config.getCameraId());
        addOption(command, "--camera-facing", config.getCameraFacing());
        addOption(command, "--camera-size", config.getCameraSize());
        addOption(command, "--camera-ar", config.getCameraAspectRatio());
        addOption(command, "--camera-fps", config.getCameraFps());
        addFlag(command, "--camera-high-speed", config.isCameraHighSpeed());

        // Display/Orientation options
        if (!config.getDisplayOrientation().equals("0")) {
            addOption(command, "--display-orientation", config.getDisplayOrientation());
        }
        if (!config.getCaptureOrientation().equals("0")) {
            addOption(command, "--capture-orientation", config.getCaptureOrientation());
        }
        addOption(command, "--angle", config.getAngle());
        addOption(command, "--orientation", config.getOrientation());
        addOption(command, "--crop", config.getCrop());

        // Virtual Display options
        addOption(command, "--new-display", config.getNewDisplay());
        addFlag(command, "--no-vd-destroy-content", config.isNoVdDestroyContent());
        addFlag(command, "--no-vd-system-decorations", config.isNoVdSystemDecorations());
        addOption(command, "--display-ime-policy", config.getDisplayImePolicy());

        // Utility options
        addFlag(command, "--list-encoders", config.isListEncoders());
        addFlag(command, "--list-displays", config.isListDisplays());
        addFlag(command, "--list-cameras", config.isListCameras());
        addFlag(command, "--list-camera-sizes", config.isListCameraSizes());
        addFlag(command, "--list-apps", config.isListApps());
        addOption(command, "--start-app", config.getStartApp());
        if (!config.getPushTarget().equals("/sdcard/Download/")) {
            addOption(command, "--push-target", config.getPushTarget());
        }
        addFlag(command, "--no-cleanup", config.isNoCleanup());
        addFlag(command, "--kill-adb-on-close", config.isKillAdbOnClose());

        // Advanced options
        if (!config.getVerbosity().equals("info")) {
            addOption(command, "--verbosity", config.getVerbosity());
        }
        addOption(command, "--render-driver", config.getRenderDriver());
        addOption(command, "--shortcut-mod", config.getShortcutMod());
        addFlag(command, "--print-fps", config.isPrintFps());
        addOption(command, "--v4l2-sink", config.getV4l2Sink());
        addOption(command, "--v4l2-buffer", config.getV4l2Buffer());
        addOption(command, "--pause-on-exit", config.getPauseOnExit());

        return command;
    }

    /**
     * Build command as a single string (for display)
     */
    public String buildCommandString() {
        List<String> command = buildCommand();
        return String.join(" ", command);
    }

    private void addOption(List<String> command, String option, String value) {
        if (value != null && !value.trim().isEmpty()) {
            command.add(option);
            command.add(value);
        }
    }

    private void addFlag(List<String> command, String flag, boolean enabled) {
        if (enabled) {
            command.add(flag);
        }
    }
}
