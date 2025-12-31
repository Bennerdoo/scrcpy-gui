package com.scrcpy.gui.config;

/**
 * Data model representing all scrcpy configuration options
 */
public class ScrcpyConfig {
    // Connection options
    private String serial;
    private boolean selectUsb;
    private boolean selectTcpip;
    private String tcpipAddress;
    private String portRange;
    private boolean forceAdbForward;
    private String tunnelHost;
    private String tunnelPort;

    // Video options
    private String videoSource = "display";
    private String videoCodec = "h264";
    private String videoEncoder;
    private String videoBitRate = "8M";
    private String maxSize;
    private String maxFps;
    private String videoBuffer;
    private String videoCodecOptions;
    private boolean noVideo;
    private boolean noVideoPlayback;
    private boolean noMipmaps;
    private boolean noDownsizeOnError;

    // Audio options
    private String audioSource = "output";
    private String audioCodec = "opus";
    private String audioEncoder;
    private String audioBitRate = "128K";
    private String audioBuffer = "50";
    private String audioOutputBuffer = "5";
    private String audioCodecOptions;
    private boolean audioDup;
    private boolean noAudio;
    private boolean noAudioPlayback;
    private boolean requireAudio;

    // Control options
    private boolean noControl;
    private boolean noPlayback;
    private boolean noClipboardAutosync;
    private boolean legacyPaste;
    private boolean preferText;
    private boolean rawKeyEvents;
    private boolean noKeyRepeat;
    private boolean noMouseHover;

    // Keyboard/Mouse/Gamepad options
    private String keyboardMode;
    private String mouseMode;
    private String mouseBindings;
    private String gamepadMode;
    private boolean otgMode;

    // Device options
    private String displayId;
    private boolean showTouches;
    private boolean stayAwake;
    private boolean turnScreenOff;
    private String screenOffTimeout;
    private boolean powerOffOnClose;
    private boolean noPowerOn;
    private boolean disableScreensaver;

    // Window options
    private String windowTitle;
    private String windowX;
    private String windowY;
    private String windowWidth;
    private String windowHeight;
    private boolean windowBorderless;
    private boolean alwaysOnTop;
    private boolean fullscreen;
    private boolean noWindow;

    // Recording options
    private String recordFile;
    private String recordFormat;
    private String recordOrientation = "0";
    private String timeLimit;

    // Camera options
    private String cameraId;
    private String cameraFacing;
    private String cameraSize;
    private String cameraAspectRatio;
    private String cameraFps;
    private boolean cameraHighSpeed;

    // Display/Orientation options
    private String displayOrientation = "0";
    private String captureOrientation = "0";
    private String angle;
    private String orientation;
    private String crop;

    // Virtual Display options
    private String newDisplay;
    private boolean noVdDestroyContent;
    private boolean noVdSystemDecorations;
    private String displayImePolicy;

    // Utility options
    private boolean listEncoders;
    private boolean listDisplays;
    private boolean listCameras;
    private boolean listCameraSizes;
    private boolean listApps;
    private String startApp;
    private String pushTarget = "/sdcard/Download/";
    private boolean noCleanup;
    private boolean killAdbOnClose;

    // Advanced options
    private String verbosity = "info";
    private String renderDriver;
    private String shortcutMod;
    private boolean printFps;
    private String v4l2Sink;
    private String v4l2Buffer;
    private String pauseOnExit;

    // Getters and Setters
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public boolean isSelectUsb() {
        return selectUsb;
    }

    public void setSelectUsb(boolean selectUsb) {
        this.selectUsb = selectUsb;
    }

    public boolean isSelectTcpip() {
        return selectTcpip;
    }

    public void setSelectTcpip(boolean selectTcpip) {
        this.selectTcpip = selectTcpip;
    }

    public String getTcpipAddress() {
        return tcpipAddress;
    }

    public void setTcpipAddress(String tcpipAddress) {
        this.tcpipAddress = tcpipAddress;
    }

    public String getPortRange() {
        return portRange;
    }

    public void setPortRange(String portRange) {
        this.portRange = portRange;
    }

    public boolean isForceAdbForward() {
        return forceAdbForward;
    }

    public void setForceAdbForward(boolean forceAdbForward) {
        this.forceAdbForward = forceAdbForward;
    }

    public String getTunnelHost() {
        return tunnelHost;
    }

    public void setTunnelHost(String tunnelHost) {
        this.tunnelHost = tunnelHost;
    }

    public String getTunnelPort() {
        return tunnelPort;
    }

    public void setTunnelPort(String tunnelPort) {
        this.tunnelPort = tunnelPort;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public String getVideoEncoder() {
        return videoEncoder;
    }

    public void setVideoEncoder(String videoEncoder) {
        this.videoEncoder = videoEncoder;
    }

    public String getVideoBitRate() {
        return videoBitRate;
    }

    public void setVideoBitRate(String videoBitRate) {
        this.videoBitRate = videoBitRate;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getMaxFps() {
        return maxFps;
    }

    public void setMaxFps(String maxFps) {
        this.maxFps = maxFps;
    }

    public String getVideoBuffer() {
        return videoBuffer;
    }

    public void setVideoBuffer(String videoBuffer) {
        this.videoBuffer = videoBuffer;
    }

    public String getVideoCodecOptions() {
        return videoCodecOptions;
    }

    public void setVideoCodecOptions(String videoCodecOptions) {
        this.videoCodecOptions = videoCodecOptions;
    }

    public boolean isNoVideo() {
        return noVideo;
    }

    public void setNoVideo(boolean noVideo) {
        this.noVideo = noVideo;
    }

    public boolean isNoVideoPlayback() {
        return noVideoPlayback;
    }

    public void setNoVideoPlayback(boolean noVideoPlayback) {
        this.noVideoPlayback = noVideoPlayback;
    }

    public boolean isNoMipmaps() {
        return noMipmaps;
    }

    public void setNoMipmaps(boolean noMipmaps) {
        this.noMipmaps = noMipmaps;
    }

    public boolean isNoDownsizeOnError() {
        return noDownsizeOnError;
    }

    public void setNoDownsizeOnError(boolean noDownsizeOnError) {
        this.noDownsizeOnError = noDownsizeOnError;
    }

    public String getAudioSource() {
        return audioSource;
    }

    public void setAudioSource(String audioSource) {
        this.audioSource = audioSource;
    }

    public String getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    public String getAudioEncoder() {
        return audioEncoder;
    }

    public void setAudioEncoder(String audioEncoder) {
        this.audioEncoder = audioEncoder;
    }

    public String getAudioBitRate() {
        return audioBitRate;
    }

    public void setAudioBitRate(String audioBitRate) {
        this.audioBitRate = audioBitRate;
    }

    public String getAudioBuffer() {
        return audioBuffer;
    }

    public void setAudioBuffer(String audioBuffer) {
        this.audioBuffer = audioBuffer;
    }

    public String getAudioOutputBuffer() {
        return audioOutputBuffer;
    }

    public void setAudioOutputBuffer(String audioOutputBuffer) {
        this.audioOutputBuffer = audioOutputBuffer;
    }

    public String getAudioCodecOptions() {
        return audioCodecOptions;
    }

    public void setAudioCodecOptions(String audioCodecOptions) {
        this.audioCodecOptions = audioCodecOptions;
    }

    public boolean isAudioDup() {
        return audioDup;
    }

    public void setAudioDup(boolean audioDup) {
        this.audioDup = audioDup;
    }

    public boolean isNoAudio() {
        return noAudio;
    }

    public void setNoAudio(boolean noAudio) {
        this.noAudio = noAudio;
    }

    public boolean isNoAudioPlayback() {
        return noAudioPlayback;
    }

    public void setNoAudioPlayback(boolean noAudioPlayback) {
        this.noAudioPlayback = noAudioPlayback;
    }

    public boolean isRequireAudio() {
        return requireAudio;
    }

    public void setRequireAudio(boolean requireAudio) {
        this.requireAudio = requireAudio;
    }

    public boolean isNoControl() {
        return noControl;
    }

    public void setNoControl(boolean noControl) {
        this.noControl = noControl;
    }

    public boolean isNoPlayback() {
        return noPlayback;
    }

    public void setNoPlayback(boolean noPlayback) {
        this.noPlayback = noPlayback;
    }

    public boolean isNoClipboardAutosync() {
        return noClipboardAutosync;
    }

    public void setNoClipboardAutosync(boolean noClipboardAutosync) {
        this.noClipboardAutosync = noClipboardAutosync;
    }

    public boolean isLegacyPaste() {
        return legacyPaste;
    }

    public void setLegacyPaste(boolean legacyPaste) {
        this.legacyPaste = legacyPaste;
    }

    public boolean isPreferText() {
        return preferText;
    }

    public void setPreferText(boolean preferText) {
        this.preferText = preferText;
    }

    public boolean isRawKeyEvents() {
        return rawKeyEvents;
    }

    public void setRawKeyEvents(boolean rawKeyEvents) {
        this.rawKeyEvents = rawKeyEvents;
    }

    public boolean isNoKeyRepeat() {
        return noKeyRepeat;
    }

    public void setNoKeyRepeat(boolean noKeyRepeat) {
        this.noKeyRepeat = noKeyRepeat;
    }

    public boolean isNoMouseHover() {
        return noMouseHover;
    }

    public void setNoMouseHover(boolean noMouseHover) {
        this.noMouseHover = noMouseHover;
    }

    public String getKeyboardMode() {
        return keyboardMode;
    }

    public void setKeyboardMode(String keyboardMode) {
        this.keyboardMode = keyboardMode;
    }

    public String getMouseMode() {
        return mouseMode;
    }

    public void setMouseMode(String mouseMode) {
        this.mouseMode = mouseMode;
    }

    public String getMouseBindings() {
        return mouseBindings;
    }

    public void setMouseBindings(String mouseBindings) {
        this.mouseBindings = mouseBindings;
    }

    public String getGamepadMode() {
        return gamepadMode;
    }

    public void setGamepadMode(String gamepadMode) {
        this.gamepadMode = gamepadMode;
    }

    public boolean isOtgMode() {
        return otgMode;
    }

    public void setOtgMode(boolean otgMode) {
        this.otgMode = otgMode;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public boolean isShowTouches() {
        return showTouches;
    }

    public void setShowTouches(boolean showTouches) {
        this.showTouches = showTouches;
    }

    public boolean isStayAwake() {
        return stayAwake;
    }

    public void setStayAwake(boolean stayAwake) {
        this.stayAwake = stayAwake;
    }

    public boolean isTurnScreenOff() {
        return turnScreenOff;
    }

    public void setTurnScreenOff(boolean turnScreenOff) {
        this.turnScreenOff = turnScreenOff;
    }

    public String getScreenOffTimeout() {
        return screenOffTimeout;
    }

    public void setScreenOffTimeout(String screenOffTimeout) {
        this.screenOffTimeout = screenOffTimeout;
    }

    public boolean isPowerOffOnClose() {
        return powerOffOnClose;
    }

    public void setPowerOffOnClose(boolean powerOffOnClose) {
        this.powerOffOnClose = powerOffOnClose;
    }

    public boolean isNoPowerOn() {
        return noPowerOn;
    }

    public void setNoPowerOn(boolean noPowerOn) {
        this.noPowerOn = noPowerOn;
    }

    public boolean isDisableScreensaver() {
        return disableScreensaver;
    }

    public void setDisableScreensaver(boolean disableScreensaver) {
        this.disableScreensaver = disableScreensaver;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public String getWindowX() {
        return windowX;
    }

    public void setWindowX(String windowX) {
        this.windowX = windowX;
    }

    public String getWindowY() {
        return windowY;
    }

    public void setWindowY(String windowY) {
        this.windowY = windowY;
    }

    public String getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(String windowWidth) {
        this.windowWidth = windowWidth;
    }

    public String getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(String windowHeight) {
        this.windowHeight = windowHeight;
    }

    public boolean isWindowBorderless() {
        return windowBorderless;
    }

    public void setWindowBorderless(boolean windowBorderless) {
        this.windowBorderless = windowBorderless;
    }

    public boolean isAlwaysOnTop() {
        return alwaysOnTop;
    }

    public void setAlwaysOnTop(boolean alwaysOnTop) {
        this.alwaysOnTop = alwaysOnTop;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public boolean isNoWindow() {
        return noWindow;
    }

    public void setNoWindow(boolean noWindow) {
        this.noWindow = noWindow;
    }

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
    }

    public String getRecordFormat() {
        return recordFormat;
    }

    public void setRecordFormat(String recordFormat) {
        this.recordFormat = recordFormat;
    }

    public String getRecordOrientation() {
        return recordOrientation;
    }

    public void setRecordOrientation(String recordOrientation) {
        this.recordOrientation = recordOrientation;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getCameraFacing() {
        return cameraFacing;
    }

    public void setCameraFacing(String cameraFacing) {
        this.cameraFacing = cameraFacing;
    }

    public String getCameraSize() {
        return cameraSize;
    }

    public void setCameraSize(String cameraSize) {
        this.cameraSize = cameraSize;
    }

    public String getCameraAspectRatio() {
        return cameraAspectRatio;
    }

    public void setCameraAspectRatio(String cameraAspectRatio) {
        this.cameraAspectRatio = cameraAspectRatio;
    }

    public String getCameraFps() {
        return cameraFps;
    }

    public void setCameraFps(String cameraFps) {
        this.cameraFps = cameraFps;
    }

    public boolean isCameraHighSpeed() {
        return cameraHighSpeed;
    }

    public void setCameraHighSpeed(boolean cameraHighSpeed) {
        this.cameraHighSpeed = cameraHighSpeed;
    }

    public String getDisplayOrientation() {
        return displayOrientation;
    }

    public void setDisplayOrientation(String displayOrientation) {
        this.displayOrientation = displayOrientation;
    }

    public String getCaptureOrientation() {
        return captureOrientation;
    }

    public void setCaptureOrientation(String captureOrientation) {
        this.captureOrientation = captureOrientation;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getNewDisplay() {
        return newDisplay;
    }

    public void setNewDisplay(String newDisplay) {
        this.newDisplay = newDisplay;
    }

    public boolean isNoVdDestroyContent() {
        return noVdDestroyContent;
    }

    public void setNoVdDestroyContent(boolean noVdDestroyContent) {
        this.noVdDestroyContent = noVdDestroyContent;
    }

    public boolean isNoVdSystemDecorations() {
        return noVdSystemDecorations;
    }

    public void setNoVdSystemDecorations(boolean noVdSystemDecorations) {
        this.noVdSystemDecorations = noVdSystemDecorations;
    }

    public String getDisplayImePolicy() {
        return displayImePolicy;
    }

    public void setDisplayImePolicy(String displayImePolicy) {
        this.displayImePolicy = displayImePolicy;
    }

    public boolean isListEncoders() {
        return listEncoders;
    }

    public void setListEncoders(boolean listEncoders) {
        this.listEncoders = listEncoders;
    }

    public boolean isListDisplays() {
        return listDisplays;
    }

    public void setListDisplays(boolean listDisplays) {
        this.listDisplays = listDisplays;
    }

    public boolean isListCameras() {
        return listCameras;
    }

    public void setListCameras(boolean listCameras) {
        this.listCameras = listCameras;
    }

    public boolean isListCameraSizes() {
        return listCameraSizes;
    }

    public void setListCameraSizes(boolean listCameraSizes) {
        this.listCameraSizes = listCameraSizes;
    }

    public boolean isListApps() {
        return listApps;
    }

    public void setListApps(boolean listApps) {
        this.listApps = listApps;
    }

    public String getStartApp() {
        return startApp;
    }

    public void setStartApp(String startApp) {
        this.startApp = startApp;
    }

    public String getPushTarget() {
        return pushTarget;
    }

    public void setPushTarget(String pushTarget) {
        this.pushTarget = pushTarget;
    }

    public boolean isNoCleanup() {
        return noCleanup;
    }

    public void setNoCleanup(boolean noCleanup) {
        this.noCleanup = noCleanup;
    }

    public boolean isKillAdbOnClose() {
        return killAdbOnClose;
    }

    public void setKillAdbOnClose(boolean killAdbOnClose) {
        this.killAdbOnClose = killAdbOnClose;
    }

    public String getVerbosity() {
        return verbosity;
    }

    public void setVerbosity(String verbosity) {
        this.verbosity = verbosity;
    }

    public String getRenderDriver() {
        return renderDriver;
    }

    public void setRenderDriver(String renderDriver) {
        this.renderDriver = renderDriver;
    }

    public String getShortcutMod() {
        return shortcutMod;
    }

    public void setShortcutMod(String shortcutMod) {
        this.shortcutMod = shortcutMod;
    }

    public boolean isPrintFps() {
        return printFps;
    }

    public void setPrintFps(boolean printFps) {
        this.printFps = printFps;
    }

    public String getV4l2Sink() {
        return v4l2Sink;
    }

    public void setV4l2Sink(String v4l2Sink) {
        this.v4l2Sink = v4l2Sink;
    }

    public String getV4l2Buffer() {
        return v4l2Buffer;
    }

    public void setV4l2Buffer(String v4l2Buffer) {
        this.v4l2Buffer = v4l2Buffer;
    }

    public String getPauseOnExit() {
        return pauseOnExit;
    }

    public void setPauseOnExit(String pauseOnExit) {
        this.pauseOnExit = pauseOnExit;
    }
}
