<div align="center">

# DeSync

**Türkiye'deki DPI engellerini kök (root) gerektirmeden aşmak için bir Android uygulaması.**
*An Android app to bypass DPI-based internet blocking in Turkey — no root required.*

*NOT: DESYNC'IN VPN KULLANMASI NEDENI BYEDPI MOTORU OLMASIDIR. VPN OLARAK ÇALIŞMAZ. IP NIZI GIZLEMEYECEKTIR VE BILGILERINIZ CIHAZINIZDAN ÇIKMAYACAKTIR*

[![Build APK](https://github.com/EmreO33/SplitWire-Turkey-Android/actions/workflows/build.yml/badge.svg)](https://github.com/EmreO33/SplitWire-Turkey-Android/actions/workflows/build.yml)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)

</div>

---

## 🇹🇷 Türkçe

### Nedir?

DeSync, operatör (ISP) düzeyindeki DPI (Derin Paket İncelemesi) engellerini aşan bir Android uygulamasıdır. Discord, çeşitli web siteleri ve başka servislerin engellenmesini aşmaya yarar. **Root gerektirmeyen** standart Android yaklaşımını kullanır:

```
Cihaz trafiği  →  Android VpnService (tun)  →  hev-socks5-tunnel  →  yerel ByeDPI proxy (127.0.0.1:1080)  →  internet
```

ByeDPI motoru paketleri bölerek/karıştırarak DPI'ın isteği analiz etmesini engeller. Trafik şifreli bir sunucuya yönlendirilmez; sadece yerel olarak işlenir.

> ⚠️ **Sorumluluk reddi:** Bu yazılım eğitim amaçlıdır. Yürürlükteki yasalara ve düzenlemelere uymak tamamen kullanıcının sorumluluğundadır.

### Özellikler

- 🔌 **Root gerektirmez** — Android VpnService ile çalışır.
- 🎯 **Hazır stratejiler** — tek dokunuşla uygulanan ön ayarlar.
- 🧠 **ByeDPI motoru** — split, disorder, fake, OOB, TLS record split, otomatik mod.
- ⚙️ **Gelişmiş seçenekler** — komut satırı argümanlarını ve tüm parametreleri elle ayarlayın.
- 📱 **Uygulama bazlı yönlendirme** — yalnızca seçtiğiniz uygulamaları (ör. sadece Discord) VPN'den geçirin.
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

1. [Releases](https://github.com/EmreO33/SplitWire-Turkey-Android/releases) sayfasından son `DeSync.apk` dosyasını indirin.
2. Telefonunuzda **"Bilinmeyen kaynaklardan yükleme"**ye izin verin.
3. APK'yı kurun, açın ve **Bağlan**'a basın; VPN iznini onaylayın.
4. Ayarlar → **Strateji ön ayarı**'ndan bir strateji seçin. İşe yaramazsa başka bir ön ayar deneyin.

---

## 🇬🇧 English

### What is it?

DeSync is an Android app that bypasses ISP-level DPI (Deep Packet Inspection) blocking of Discord, websites, and other services. It uses the standard **no-root** Android approach:

```
Device traffic  →  Android VpnService (tun)  →  hev-socks5-tunnel  →  local ByeDPI proxy (127.0.0.1:1080)  →  internet
```

The ByeDPI engine splits/disorders packets so DPI cannot properly analyze the request. Traffic is not routed to a remote encrypted server — it is only processed locally.

> ⚠️ **Disclaimer:** This software is for educational purposes. You are solely responsible for complying with applicable laws and regulations.

### Features

- 🔌 **No root required** — built on Android `VpnService`.
- 🎯 **Ready-made presets** — one-tap bypass strategies.
- 🧠 **ByeDPI engine** — split, disorder, fake, OOB, TLS record split, auto mode.
- ⚙️ **Advanced options** — hand-tune command-line arguments and every parameter.
- 📱 **Per-app routing** — send only the apps you pick (e.g. just Discord) through the VPN.
- 📊 **Connection stats** — uptime and session data usage.
- 🌗 **Light/dark theme** — one tap from the toolbar.
- 🌐 **DNS setting** — defaults to Google DNS (8.8.8.8).
- 🇹🇷🇬🇧 **Turkish and English UI.**

See the preset table above. Install `DeSync.apk` from [Releases](https://github.com/EmreO33/SplitWire-Turkey-Android/releases), allow installation from unknown sources, open the app, tap **Connect**, grant VPN permission, and pick a strategy. If one strategy doesn't work on your ISP, try another preset.

### Building from source

You don't need a local Android SDK — the APK is **built in the cloud by GitHub Actions** on every push to `main` and every `v*` tag. Download it from the run's "Artifacts" section, or from Releases when you publish a tag. For local builds use JDK 17, Android SDK 34, NDK `26.1.10909125`, CMake `3.22.1`, then `./gradlew assembleRelease`.

To cut a release:

```bash
git tag v1.2.0
git push origin v1.2.0
```

---

## Credits & License

DeSync is built on top of excellent open-source work:

- **[ByeDPIAndroid](https://github.com/dovecoteescapee/ByeDPIAndroid)** by dovecoteescapee — the Android app architecture this project is based on (GPL-3.0).
- **[ByeDPI](https://github.com/hufrea/byedpi)** by hufrea — the DPI-bypass engine.
- **[hev-socks5-tunnel](https://github.com/heiher/hev-socks5-tunnel)** by heiher — tun-to-SOCKS5 tunneling.
- **[SplitWire-Turkey](https://github.com/cagritaskn/SplitWire-Turkey)** by cagritaskn — the original Windows project and inspiration.

Licensed under the **GNU General Public License v3.0** — see [LICENSE](LICENSE). As required by the GPL, the source for the bundled native components is included in this repository under `app/src/main/cpp/byedpi` and `app/src/main/jni/hev-socks5-tunnel`, each retaining its own license file.
