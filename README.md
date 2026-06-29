<div align="center">

<img src=".github/images/icon.png" width="120" alt="DeSync icon" />

# DeSync

**Türkiye'deki DPI engellerini kök (root) gerektirmeden aşan bir Android uygulaması.**
*An Android app that bypasses DPI-based internet blocking in Turkey — no root required.*

[![Build APK](https://github.com/EmreO33/SplitWire-Turkey-Android/actions/workflows/build.yml/badge.svg)](https://github.com/EmreO33/SplitWire-Turkey-Android/actions/workflows/build.yml)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)

</div>

> **🇹🇷 Önemli:** DeSync bir gizlilik/VPN uygulaması **değildir**. Android'in VpnService'ini yalnızca cihaz trafiğini yerel ByeDPI motoruna yönlendirmek için kullanır. IP adresinizi **gizlemez** ve hiçbir veriniz cihazınızdan **dışarı çıkmaz**.
>
> **🇬🇧 Important:** DeSync is **not** a privacy VPN. It uses Android's VpnService only as plumbing to route device traffic through the local ByeDPI engine. It does **not** hide your IP address, and none of your data leaves your device.

---

## 🇹🇷 Türkçe

### Nedir?

DeSync, operatör (ISP) düzeyindeki DPI (Derin Paket İncelemesi) engellerini aşan bir Android uygulamasıdır. Discord, çeşitli web siteleri ve diğer servislerin engellenmesini aşmaya yarar. **Root gerektirmeyen** standart Android yaklaşımını kullanır:

```
Cihaz trafiği  →  Android VpnService (tun)  →  hev-socks5-tunnel  →  yerel ByeDPI proxy (127.0.0.1:1080)  →  internet
```

ByeDPI motoru paketleri bölerek/karıştırarak DPI'ın isteği analiz etmesini engeller. Trafik uzaktaki bir sunucuya yönlendirilmez; yalnızca cihaz üzerinde yerel olarak işlenir.

> ⚠️ **Sorumluluk reddi:** Bu yazılım eğitim amaçlıdır. Yürürlükteki yasalara ve düzenlemelere uymak tamamen kullanıcının sorumluluğundadır.

### Özellikler

- 🔌 **Root gerektirmez** — Android VpnService ile çalışır.
- 🎯 **Hazır stratejiler** — tek dokunuşla uygulanan ön ayarlar.
- 🧠 **ByeDPI motoru** — split, disorder, fake, OOB, TLS record split, otomatik mod.
- ⚙️ **Gelişmiş seçenekler** — komut satırı argümanlarını ve tüm parametreleri elle ayarlayın.
- 📱 **Uygulama bazlı yönlendirme** — yalnızca seçtiğiniz uygulamaları (ör. sadece Discord) yönlendirin.
- 📊 **Bağlantı istatistikleri** — bağlantı süresi ve oturum veri kullanımı.
- 🌗 **Açık/koyu tema** — araç çubuğundan tek dokunuşla.
- 🌐 **DNS ayarı** — varsayılan olarak Google DNS (8.8.8.8).
- 🇹🇷🇬🇧 **Türkçe ve İngilizce arayüz.**

### Strateji ön ayarları

| Ön ayar | ByeDPI argümanları | Açıklama |
|---|---|---|
| Önerilen | `--split 1 --disorder 1` | Çoğu durumda iyi çalışan varsayılan. |
| Otomatik | `--disorder 1 --auto=torst --tlsrec 1+s` | Engel/bozulma algılanınca otomatik strateji uygular. |
| Discord | `--fake -1 --ttl 8` | Sahte paket + düşük TTL. |
| SNI'de böl | `--split 1+s --disorder 1+s` | İsteği SNI konumunda böler. |
| TLS kaydı böl | `--tlsrec 1+s` | ClientHello'yu ayrı TLS kayıtlarına böler. |
| Özel | — | Gelişmiş seçenekler / komut satırı düzenleyicisini kullanır. |

> Not: Hiçbir ön ayar proxy portunu değiştirmez; port **1080** olarak kalır (VPN tüneli bunu bekler).

### Kurulum

1. [Releases](https://github.com/EmreO33/SplitWire-Turkey-Android/releases) sayfasından en güncel `DeSync.apk` dosyasını indirin.
2. Telefonunuzda **"Bilinmeyen kaynaklardan yükleme"**ye izin verin.
3. APK'yı kurun, açın ve **Bağlan**'a basın; VPN iznini onaylayın.
4. Ayarlar → **Strateji ön ayarı**'ndan bir strateji seçin. İşe yaramazsa başka bir ön ayar deneyin.

### Kaynaktan derleme

Yerel olarak Android SDK kurmaya gerek yok — APK, her `main` push'unda ve her `v*` etiketinde **GitHub Actions ile bulutta** derlenir. Çalışmanın "Artifacts" bölümünden ya da bir etiket yayınladığınızda Releases sayfasından indirin. Yerel derleme için JDK 17, Android SDK 34, NDK `26.1.10909125`, CMake `3.22.1` gerekir; ardından `./gradlew assembleRelease`.

---

## 🇬🇧 English

### What is it?

DeSync is an Android app that bypasses ISP-level DPI (Deep Packet Inspection) blocking of Discord, websites, and other services. It uses the standard **no-root** Android approach:

```
Device traffic  →  Android VpnService (tun)  →  hev-socks5-tunnel  →  local ByeDPI proxy (127.0.0.1:1080)  →  internet
```

The ByeDPI engine splits/disorders packets so DPI cannot properly analyze the request. Traffic is not routed to a remote server; it is only processed locally on the device.

> ⚠️ **Disclaimer:** This software is for educational purposes. You are solely responsible for complying with applicable laws and regulations.

### Features

- 🔌 **No root required** — built on Android `VpnService`.
- 🎯 **Ready-made presets** — one-tap bypass strategies.
- 🧠 **ByeDPI engine** — split, disorder, fake, OOB, TLS record split, auto mode.
- ⚙️ **Advanced options** — hand-tune command-line arguments and every parameter.
- 📱 **Per-app routing** — route only the apps you pick (e.g. just Discord).
- 📊 **Connection stats** — uptime and session data usage.
- 🌗 **Light/dark theme** — one tap from the toolbar.
- 🌐 **DNS setting** — defaults to Google DNS (8.8.8.8).
- 🇹🇷🇬🇧 **Turkish and English UI.**

### Strategy presets

| Preset | ByeDPI arguments | Description |
|---|---|---|
| Recommended | `--split 1 --disorder 1` | Sensible default that works in most cases. |
| Auto | `--disorder 1 --auto=torst --tlsrec 1+s` | Applies a bypass strategy automatically when blocking/breakage is detected. |
| Discord | `--fake -1 --ttl 8` | Fake packet + low TTL. |
| Split at SNI | `--split 1+s --disorder 1+s` | Splits the request at the SNI position. |
| TLS record split | `--tlsrec 1+s` | Splits the ClientHello into separate TLS records. |
| Custom | — | Uses the Advanced options / command-line editor. |

> Note: none of the presets change the proxy port; it stays at **1080** (the VPN tunnel expects this).

### Installation

1. Download the latest `DeSync.apk` from [Releases](https://github.com/EmreO33/SplitWire-Turkey-Android/releases).
2. Allow **"install from unknown sources"** on your phone.
3. Install the APK, open it, tap **Connect**, and grant the VPN permission.
4. Go to Settings → **Strategy preset** and pick a strategy. If one doesn't work on your ISP, try another.

### Building from source

You don't need a local Android SDK — the APK is **built in the cloud by GitHub Actions** on every push to `main` and every `v*` tag. Download it from the run's "Artifacts" section, or from Releases when you publish a tag. For local builds use JDK 17, Android SDK 34, NDK `26.1.10909125`, CMake `3.22.1`, then `./gradlew assembleRelease`.

To cut a release:

```bash
git tag v1.2.3
git push origin v1.2.3
```

---

## Credits & License

DeSync is built on top of excellent open-source work:

- **[ByeDPIAndroid](https://github.com/dovecoteescapee/ByeDPIAndroid)** by dovecoteescapee — the Android app architecture this project is based on (GPL-3.0).
- **[ByeDPI](https://github.com/hufrea/byedpi)** by hufrea — the DPI-bypass engine.
- **[hev-socks5-tunnel](https://github.com/heiher/hev-socks5-tunnel)** by heiher — tun-to-SOCKS5 tunneling.
- **[SplitWire-Turkey](https://github.com/cagritaskn/SplitWire-Turkey)** by cagritaskn — the original Windows project and inspiration.

Licensed under the **GNU General Public License v3.0** — see [LICENSE](LICENSE). As required by the GPL, the source for the bundled native components is included in this repository under `app/src/main/cpp/byedpi` and `app/src/main/jni/hev-socks5-tunnel`, each retaining its own license file.
