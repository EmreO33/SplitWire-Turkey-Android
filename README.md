<div align="center">

# SplitWire-Turkey (Android)

**Türkiye'deki DPI engellerini kök (root) gerektirmeden aşmak için bir Android uygulaması.**
*An Android app to bypass DPI-based internet blocking in Turkey — no root required.*

[![Build APK](https://github.com/EmreO33/SplitWire-Turkey-Android/actions/workflows/build.yml/badge.svg)](https://github.com/EmreO33/SplitWire-Turkey-Android/actions/workflows/build.yml)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)

</div>

---

## 🇹🇷 Türkçe

### Nedir?

SplitWire-Turkey (Android), [SplitWire-Turkey](https://github.com/cagritaskn/SplitWire-Turkey) Windows uygulamasının ruhunu Android'e taşıyan bir DPI (Derin Paket İncelemesi) aşma aracıdır. Discord, çeşitli web siteleri ve başka servislerin operatör (ISP) düzeyinde engellenmesini aşmaya yarar.

Windows sürümü çekirdek seviyesinde paket işleme (WinDivert + zapret/GoodbyeDPI/ByeDPI) ve WireGuard kullanır. Bunların çoğunun Android'de doğrudan karşılığı yoktur. Bu uygulama, Android'de **root gerektirmeyen** standart yaklaşımı kullanır:

```
Cihaz trafiği  →  Android VpnService (tun)  →  hev-socks5-tunnel  →  yerel ByeDPI proxy (127.0.0.1:1080)  →  internet
```

ByeDPI, Windows sürümüyle **aynı motordur** ve paketleri bölerek/karıştırarak DPI'ın isteği analiz etmesini engeller. Trafik şifreli bir sunucuya yönlendirilmez; sadece yerel olarak işlenir.

> ⚠️ **Sorumluluk reddi:** Bu yazılım eğitim amaçlıdır. Yürürlükteki yasalara ve düzenlemelere uymak tamamen kullanıcının sorumluluğundadır.

### Özellikler

- 🔌 **Root gerektirmez** — Android VpnService ile çalışır.
- 🎯 **Türkiye'ye göre hazır stratejiler** — tek dokunuşla uygulanan ön ayarlar.
- 🧠 **ByeDPI motoru** — split, disorder, fake, OOB, TLS record split, otomatik mod.
- 🛠️ **Gelişmiş düzenleyici** — dilerseniz komut satırı argümanlarını elle girin.
- 🌐 **DNS ayarı** — varsayılan olarak Google DNS (8.8.8.8).
- 🇹🇷🇬🇧 **Türkçe ve İngilizce arayüz.**

### Strateji ön ayarları

| Ön ayar | ByeDPI argümanları | Açıklama |
|---|---|---|
| Önerilen | `--split 1 --disorder 1` | Windows sürümünün varsayılanı; çoğu durumda iyi çalışır. |
| Otomatik | `--disorder 1 --auto=torst --tlsrec 1+s` | Engel/bozulma algılanınca otomatik strateji uygular. |
| Discord | `--fake -1 --ttl 8` | Sahte paket + düşük TTL. |
| SNI'de böl | `--split 1+s --disorder 1+s` | İsteği SNI konumunda böler. |
| TLS kaydı böl | `--tlsrec 1+s` | ClientHello'yu ayrı TLS kayıtlarına böler. |
| Özel | — | Manuel arayüz/komut satırı düzenleyicisini kullanır. |

> Not: Hiçbir ön ayar proxy portunu değiştirmez; port **1080** olarak kalır (VPN tüneli bunu bekler).

### Kurulum

1. [Releases](https://github.com/EmreO33/SplitWire-Turkey-Android/releases) sayfasından son `SplitWire-Turkey-debug.apk` dosyasını indirin.
2. Telefonunuzda **"Bilinmeyen kaynaklardan yükleme"**ye izin verin.
3. APK'yı kurun, açın ve **Bağlan**'a basın; VPN iznini onaylayın.
4. Ayarlar → **Strateji ön ayarı**'ndan bir strateji seçin. İşe yaramazsa başka bir ön ayar deneyin.

### Kaynaktan derleme

Yerel olarak Android SDK kurmaya gerek yok — her `main` push'unda ve `v*` etiketinde APK **GitHub Actions ile bulutta** derlenir. APK'yı Actions çalışmasının "Artifacts" bölümünden veya bir sürüm yayınladığınızda Releases'ten indirin.

Yeni bir sürüm yayınlamak için:

```bash
git tag v1.0.0
git push origin v1.0.0
```

Android Studio ile yerel derleme için: JDK 17, Android SDK 34, NDK `26.1.10909125`, CMake `3.22.1` gerekir, ardından `./gradlew assembleDebug`.

---

## 🇬🇧 English

### What is it?

SplitWire-Turkey (Android) brings the spirit of the Windows [SplitWire-Turkey](https://github.com/cagritaskn/SplitWire-Turkey) tool to Android. It bypasses ISP-level DPI blocking of Discord, websites, and other services.

The Windows version relies on kernel-level packet manipulation (WinDivert + zapret/GoodbyeDPI/ByeDPI) and WireGuard, most of which has no direct Android equivalent. This app uses the standard **no-root** Android approach:

```
Device traffic  →  Android VpnService (tun)  →  hev-socks5-tunnel  →  local ByeDPI proxy (127.0.0.1:1080)  →  internet
```

ByeDPI is the **same engine** the Windows version uses; it splits/disorders packets so DPI cannot properly analyze the request. Traffic is not routed to a remote encrypted server — it is only processed locally.

> ⚠️ **Disclaimer:** This software is for educational purposes. You are solely responsible for complying with applicable laws and regulations.

### Features

- 🔌 **No root required** — built on Android `VpnService`.
- 🎯 **Turkey-tuned presets** — one-tap bypass strategies.
- 🧠 **ByeDPI engine** — split, disorder, fake, OOB, TLS record split, auto mode.
- 🛠️ **Advanced editor** — enter raw command-line arguments if you prefer.
- 🌐 **DNS setting** — defaults to Google DNS (8.8.8.8).
- 🇹🇷🇬🇧 **Turkish and English UI.**

See the preset table above. Install the `SplitWire-Turkey-debug.apk` from [Releases](https://github.com/EmreO33/SplitWire-Turkey-Android/releases), allow installation from unknown sources, open the app, tap **Connect**, grant VPN permission, and pick a strategy. If one strategy doesn't work on your ISP, try another preset.

### Building from source

You don't need a local Android SDK — the APK is **built in the cloud by GitHub Actions** on every push to `main` and every `v*` tag. Download it from the run's "Artifacts" section, or from Releases when you publish a tag. For local builds use JDK 17, Android SDK 34, NDK `26.1.10909125`, CMake `3.22.1`, then `./gradlew assembleDebug`.

---

## Credits & License

This project is a fork/rebrand built on top of excellent open-source work:

- **[ByeDPIAndroid](https://github.com/dovecoteescapee/ByeDPIAndroid)** by dovecoteescapee — the Android app architecture this fork is based on (GPL-3.0).
- **[ByeDPI](https://github.com/hufrea/byedpi)** by hufrea — the DPI-bypass engine.
- **[hev-socks5-tunnel](https://github.com/heiher/hev-socks5-tunnel)** by heiher — tun-to-SOCKS5 tunneling.
- **[SplitWire-Turkey](https://github.com/cagritaskn/SplitWire-Turkey)** by cagritaskn — the original Windows project and inspiration.

Licensed under the **GNU General Public License v3.0** — see [LICENSE](LICENSE). As required by the GPL, the source for the bundled native components is included in this repository under `app/src/main/cpp/byedpi` and `app/src/main/jni/hev-socks5-tunnel`, each retaining its own license file.
