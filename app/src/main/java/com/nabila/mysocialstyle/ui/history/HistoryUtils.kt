package com.nabila.mysocialstyle.ui.history

object HistoryUtils {
    fun getStyleDescription(style: String): String {
        return when (style.lowercase()) {
            "expressive" -> """
                Expressive adalah orang yang komunikatif, suka bersosialisasi, dan terbuka terhadap ide-ide baru.

                Cara berkomunikasi dengan Expressive:
                😄 Tunjukkan antusiasme saat berbicara.
                🧏‍♀️ Dengarkan dengan penuh perhatian.
                ✨ Gunakan pendekatan yang ramah dan tidak kaku.
                🗣️ Ajak diskusi terbuka, beri ruang untuk mereka berbagi ide.
                
                Peran yang sesuai:
                🌟 Media dan Informasi: Mengelola konten media sosial secara kreatif dan menarik.
                🎤 Moderator: Membawakan diskusi dan seminar dengan gaya komunikatif yang memikat.
                💡 Koordinator Acara: Mengembangkan konsep kreatif untuk acara, memotivasi tim.
            """.trimIndent()

            "driver" -> """
                Driver adalah orang yang tegas, fokus pada tujuan, dan cepat dalam mengambil keputusan.

                Cara berkomunikasi dengan Driver:
                🧠 Sampaikan langsung ke inti pembicaraan.
                ⏱️ Hindari basa-basi yang tidak perlu.
                📊 Gunakan data atau fakta jika perlu mendukung argumen.
                💼 Tunjukkan kepercayaan diri saat menyampaikan ide.
                
                Peran yang sesuai:
                👑 Ketua atau Wakil: Memimpin organisasi dengan visi yang jelas.
                🎯 Koordinator Program Kerja: Memimpin dan memastikan program kerja berjalan sesuai target.
                🚀 Motivator Tim: Memotivasi anggota untuk mencapai target.
            """.trimIndent()

            "amiable" -> """
                Amiable adalah orang yang sabar, mendengarkan dengan baik, dan menghindari konflik.

                Cara berkomunikasi dengan Amiable:
                🤝 Bangun hubungan yang hangat dan penuh empati.
                🧘 Hindari nada suara yang agresif atau tergesa-gesa.
                👂 Tunjukkan bahwa kamu menghargai pendapat mereka.
                🌿 Beri waktu untuk mereka menyampaikan pendapat dengan nyaman.
                
                Peran yang sesuai:
                💖 Keanggotaan: Mendengarkan aspirasi dan keluhan mahasiswa.
                🌐 Humas: Membangun hubungan baik dengan pihak eksternal.
                🎉 Koordinator Lapangan: Mengatur kegiatan yang mempererat kebersamaan.
            """.trimIndent()

            "analytical" -> """
                Analytical adalah orang yang logis, teliti, dan suka menganalisis sebelum bertindak.

                Cara berkomunikasi dengan Analytical:
                📄 Sampaikan informasi secara rinci dan terstruktur.
                🔍 Bersiap untuk menjawab pertanyaan logis dan detail.
                🧩 Jangan terburu-buru minta keputusan.
                📚 Tunjukkan bahwa kamu paham topik yang dibicarakan.
                
                Peran yang sesuai:
                🔬 Riset Teknologi: Mengolah data hasil survei untuk pengembangan program kerja.
                📝 Sekretaris: Menyusun notulensi rapat yang lengkap dan terstruktur.
                💰 Bendahara: Mengelola anggaran dengan teliti, membuat laporan keuangan yang detail.
            """.trimIndent()

            else -> "Tidak diketahui"
        }
    }
}