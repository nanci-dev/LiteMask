# LiteMask

“LiteMask”是一款辅助于“KernelSU”的轻量级管理器，用途包括模块管理、超级用户授权。

## 功能
- 读取 KernelSU 模块列表
- 分析高风险 Root 授权应用
- 管理超级用户权限
- KSU 风格 UI

## 项目结构

仓库文件是按 Android 项目结构存放的，对应路径如下：
app/
├── src/main/AndroidManifest.xml
├── src/main/java/com/huanling/app/
│   ├── MainActivity.java
│   ├── splashactivity.java
│   ├── announcementdialog.java
│   ├── NetworkBackgroundView.java
│   └── GlobalApplication.java
└── src/main/res/
├── layout/activity_main.xml
├── layout/activity_splash.xml
├── layout/dialog_announcement.xml
├── drawable/bg_*.xml
├── values/colors.xml, strings.xml, styles.xml
├── anim/dialog_in.xml, dialog_out.xml
└── mipmap-hdpi/ic_launcher.png
## 编译

把上面的文件按路径放入 Android 项目对应目录，然后用 AIDE 或 CodeAssist 打开编译。

## 开源协议

本项目采用 MIT License 开源。

你可以自由使用、修改、分发本项目的代码，但需保留原作者版权声明。

完整协议内容见仓库根目录的 LICENSE 文件。
