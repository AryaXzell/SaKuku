# SakuKu

[![Build Debug APK](https://github.com/aryaxzell/sakuku/actions/workflows/build.yml/badge.svg)](https://github.com/aryaxzell/sakuku/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Pencatat keuangan harian yang simpel buat anak SMA Indonesia. Native Android, 100% offline, datanya cuma ada di HP kamu.

- Catat transaksi cepat (target: kurang dari 5 detik)
- Kategori otomatis dari keterangan ("beli bakso" jadi Jajan)
- Riwayat per tanggal dengan swipe-to-delete dan Undo
- Laporan bulanan dengan pie chart per kategori
- Bahasa santai, dark mode ikut sistem

## Status

Project masih dalam pengerjaan. Lapisan data dan utilities sudah ada, layar-layar UI (Home, Riwayat, Laporan, Tambah Transaksi) menyusul. Lihat [CHANGELOG.md](CHANGELOG.md) untuk perkembangannya.

## Tech stack

Kotlin, Jetpack Compose + Material 3, Room, ViewModel + StateFlow, Navigation Compose, Coroutines + Flow. Single module, tanpa Hilt/Dagger. minSdk 24, targetSdk 34.

## Prasyarat

- Android Studio Hedgehog (2023.1.1) atau lebih baru
- JDK 17
- Android SDK Platform 34

## Cara menjalankan

```bash
git clone https://github.com/aryaxzell/sakuku.git
cd sakuku
```

Buka folder `sakuku` di Android Studio, tunggu Gradle sync selesai, lalu Run di emulator atau device.

## Build APK lewat CLI

```bash
./gradlew assembleDebug
# hasil: app/build/outputs/apk/debug/app-debug.apk
```

Install ke device yang tersambung:

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

Setiap push dan pull request ke `main` juga di-build otomatis lewat GitHub Actions. APK debug-nya bisa diunduh dari tab **Actions**, di bagian Artifacts (`sakuku-debug-apk`, disimpan 14 hari).

## Build release

Belum ada signing config di repo ini. Buat keystore sendiri, lalu simpan kredensialnya di `keystore.properties` (sudah di-ignore git):

```properties
storeFile=/path/ke/sakuku-release.jks
storePassword=GANTI_INI
keyAlias=GANTI_INI
keyPassword=GANTI_INI
```

Lalu tambahkan `signingConfigs` yang membaca file itu di `app/build.gradle.kts` dan pasang ke build type `release`. Setelah itu:

```bash
./gradlew assembleRelease
```

Jangan pernah commit file `.jks`, `.keystore`, atau `keystore.properties`.

## Struktur project

```
app/src/main/java/com/sakuku/app/
├── data/        Room (Entity, Dao, Database) dan Repository
├── domain/      Model, Category, CategoryDetector
├── ui/          Theme, Navigation, dan tiap layar + ViewModel
└── util/        CurrencyFormatter, DateFormatter
```

## Tampilan (rencana)

Bottom navigation dengan tiga tab: **Home**, **Riwayat**, **Laporan**, plus tombol (+) untuk tambah transaksi.

- **Home:** kartu saldo, total masuk dan keluar bulan ini, 10 transaksi terakhir
- **Riwayat:** semua transaksi dikelompokkan per tanggal, bisa difilter
- **Laporan:** pilih bulan, ringkasan, pie chart pengeluaran per kategori

Screenshot akan ditambahkan setelah UI selesai.

## Kontribusi

Lihat [CONTRIBUTING.md](CONTRIBUTING.md). Laporan celah keamanan: [SECURITY.md](SECURITY.md).

## Lisensi

[MIT](LICENSE) © 2026 Arya Vallencia
