# Kontribusi ke SakuKu

Makasih udah mau bantu. Panduan singkatnya:

## Mulai

1. Fork repo ini, lalu clone fork kamu.
2. Buat branch dari `main`, contoh `fitur/target-tabungan` atau `fix/format-rupiah`.
3. Pastikan `./gradlew assembleDebug` sukses sebelum bikin Pull Request.

## Aturan kode

- Kotlin dan Jetpack Compose saja, tanpa XML layout dan tanpa Java.
- Jangan hardcode string di Compose. Semua teks masuk ke `strings.xml`.
- Gaya bahasa UI santai ala anak SMA, jangan formal.
- Tetap single module dengan ViewModel + Repository. Jangan tambah Hilt/Dagger atau backend.
- Aplikasi harus tetap 100% offline.

## Commit

Pesan commit pendek dan jelas, contoh:

```
Tambah filter pemasukan di layar Riwayat
Perbaiki format ribuan saat nominal dihapus
```

## Pull Request

- Satu PR untuk satu perubahan.
- Isi template PR yang muncul otomatis.
- Sertakan screenshot kalau ada perubahan tampilan.
- Check GitHub Actions harus hijau.

## Lapor bug atau usul fitur

Pakai [Issues](https://github.com/aryaxzell/sakuku/issues) dan pilih template yang sesuai.

Dengan berkontribusi, kamu setuju kontribusimu dirilis dengan lisensi [MIT](LICENSE) dan mengikuti [Code of Conduct](CODE_OF_CONDUCT.md).
