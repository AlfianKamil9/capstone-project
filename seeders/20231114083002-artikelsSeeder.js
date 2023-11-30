'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface) {
    await queryInterface.bulkInsert(
      'Artikels',
      [
        {
          title: 'Mengenal Baby Blues Syndrome: Penyebab, Gejala, dan Cara Mengatasi',
          image: 'https://storage.googleapis.com/models-mechine-learning-ch2-ps134/image_artikel/mengenal-baby-blues.jpg',
          subTitle: 'Mengenal Baby Blues Syndrome',
          content: `

          Sindrom baby blues merupakan kondisi psikologis yang muncul pada masa nifas dan dapat menyebabkan depresi dan kecemasan pada ibu. Menurut jurnal ilmiah berjudul How to Cope With Baby Blues: A Case Report dalam Journal of Psychiatry Psychology and Behavioral Research, 50-85 persen ibu mengalami baby blues setelah melahirkan. Umumnya kondisi ini muncul antara hari ke 1-5 dan dapat mereda dalam 10 hari. Meskipun sebagian besar wanita dapat pulih dengan sendirinya tanpa perawatan profesional, ada beberapa wanita yang mengalami kondisi yang lebih serius. Contohnya seperti gangguan kecemasan atau depresi perinatal yang memerlukan perhatian medis. Bila tidak mendapatkan penanganan yang baik, kondisi ini bisa membahayakan kesehatan ibu dan bayi. Baby blues berkaitan dengan perubahan emosional dan fisik yang terjadi saat melahirkan. 
          Meski lumrah terjadi pada ibu pasca melahirkan, tetapi perasaan sedih, marah, khawatir, cemas, dan sejenisnya perlu mendapat perhatian dari ibu dan ayah agar dapat ditangani dengan baik.

          Penyebab Baby Blues Syndrome

          Walaupun belum diketahui secara pasti, ada beberapa hal yang menjadi penyebab terjadinya baby blues, yaitu:
          1. Adaptasi menjadi ibu
          Kesulitan beradaptasi dengan peran baru sebagai seorang ibu juga dapat meningkatkan risiko baby blues. Terutama terjadi jika ibu juga harus melakukan tanggung jawab dengan rutinitas sehari-hari. Kurang tidur juga bisa memicu gejala syndrome baby blues ini, seperti perasaan sedih dan mudah tersinggung. Untuk kamu yang baru jadi ibu, simak artikel Belajar Menjadi Ibu Baru? Ini 7 Tips yang Bisa Ibu Tiru agar bisa membantumu menjalani keseharian sebagai ibu bagi sang buah hati
          2. Perubahan hormon
          Setelah melahirkan, ada perubahan hormon di dalam tubuh yang memengaruhi perasaan atau suasana hati ibu. Penurunan kadar estrogen dan progesteron atau hormon lainnya yang diproduksi kelenjar tiroid, dapat menyebabkan ibu menjadi mudah lelah, perubahan emosi, hingga depresi.
          3. Kelelahan dan kurangnya istirahat
          Perasaan depresi juga bisa muncul akibat perubahan pola tidur selama masa merawat bayi. Selain itu, kurangnya dukungan baik dari keluarga atau lingkungan sekitar, juga bisa memicu terjadinya syndrome baby blues. Alhasil, kondisi ini bisa membuat ibu kelelahan karena kurang istirahat. Untuk bisa menjaga kebugaran tubuh, diperlukan olahraga yang bisa membuat badan lebih sehat dan tidak mudah lelah. Artikel yang membahas 5 Jenis Olahraga yang bisa Dilakukan bersama Balita bisa kamu baca di sin
          4. Memiliki riwayat masalah mental
          Beberapa masalah mental yang bisa memicu syndrome baby blues adalah gangguan kecemasan, mengidap stres sebelumnya, ataupun bipolar. 
          
          Gejala Baby Blues Syndrome 
          
          Baby blues pada ibu bisa muncul dengan beberapa gejala, seperti:
          1. Muncul rasa sedih yang menyebabkan ibu menangis dan merasa depresi. Jadi, bila kamu pernah bertanya-tanya mengapa ibu baru melahirkan sering menangis? Mungkin saja ibu tersebut mengalami sindrom baby blues. Tidak hanya sering menangis, ibu yang mengalami kondisi tersebut pun akan mudah merasa cemas, mudah tersinggung, bahkan tidak memperhatikan keadaan anak atau takut menyentuh anak.
          2. Emosi labil dan mudah marah
          Setelah melahirkan, tubuh seorang ibu mengalami perubahan hormon yang signifikan. Hormon seperti estrogen, progesteron, dan hormon tiroid dapat berfluktuasi dengan cepat. 
          Perubahan ini dapat memengaruhi suasana hati dan emosi, menyebabkan ibu merasa lebih sensitif, mudah marah, dan cenderung mengalami perubahan emosi yang ekstrem.
          3. Merasa kelelahan, sulit tidur dan sering sakit kepala
          Perawatan bayi yang intensif, kurangnya tidur yang cukup, dan perubahan gaya hidup yang signifikan dapat menyebabkan tingkat stres dan kelelahan yang tinggi pada seorang ibu. Kelelahan yang berlebihan dapat memengaruhi keseimbangan emosi dan membuat ibu lebih cepat merasa lelah dan sakit kepala.
          4. Merasa kurang percaya diri dan muncul kecemasan
          Melahirkan membawa perubahan besar dalam hidup seorang wanita. Ibu baru harus beradaptasi dengan peran dan tanggung jawab baru sebagai seorang ibu, yang dapat memengaruhi identitas dan keseimbangan emosionalnya. Perasaan ketidakpastian, rasa tidak mampu, tidak percaya diri, atau merasa kewalahan dengan tanggung jawab baru dapat menyebabkan ketegangan emosional dan perasaan takut yang tidak beralasan.
          5. Kecemasan dan ketakutan yang tidak beralasan
          Ibu yang mengalami baby blues syndrome sering merasakan kecemasan dan ketakutan yang tidak beralasan terkait perawatan dan kehidupan bayinya. Ibu mungkin merasa cemas berlebihan tentang keselamatan dan kesehatan bayi, bahkan jika tidak ada alasan nyata yang mengindikasikan adanya ancaman.
          
          Namun, tidak hanya bisa terjadi pada ibu, sindrom tersebut juga bisa terjadi pada ayah. Lantas, baby blues syndrome bisa berlangsung sampai bayi umur berapa? Umumnya, sindrom ini hanya berlangsung sementara, yaitu sekitar dua hari sampai tiga minggu sejak kelahiran bayi. 

          Cara Mengatasi Baby Blues Syndrome
          
          Munculnya sindrom baby blues memang umum terjadi pada ibu usai melahirkan. Namun ini bisa memberikan dampak negatif tidak hanya bagi ibu, tapi juga bayi yang baru lahir. Bagaimana cara mengatasi ibu yang mengalami baby blues?
          1. Pola makan sehat 
          Mengonsumsi makanan dalam interval teratur dan mencukupi kebutuhan energi tubuh dapat membantu menjaga kadar gula darah tetap stabil. Kelaparan dan hipoglikemia (kadar gula darah rendah) dapat memengaruhi suasana hati dan menyebabkan perubahan emosi yang drastis. 
          2. Istirahat cukup
          Menurut studi berjudul The Baby Blues and Postnatal Depression yang dalam Andalas Journal of Public Health, istirahat yang cukup dan membiarkan ibu mengeluarkan isi hatinya, dapat mencegah syndrome baby blues yang berkepanjangan.
          3. Mencari banyak informasi seputar persalinan 
          Ini penting agar ibu tidak merasa “kaget” saat mulai merawat Si Kecil. Bicarakan dengan dokter mengenai cara merawat Si Kecil sekaligus menjaga kesehatannya. Ketika ibu tahu cara dan siap merawat Si Kecil, maka syndrome baby blues  pun dapat ibu hindari.
          4. Berbagi beban bersama pasangan atau keluarga
          Ini merupakan cara terbaik untuk menghindari sindrom pasca melahirkan. Bicarakan masalah merawat Si Kecil serta berbagi tanggung jawab dengan pasangan dapat meringankan beban ibu, baik secara fisik maupun psikis.
          5. Bergabung dengan komunitas 
          Menurut jurnal ilmiah berjudul How to Cope with Baby Blues: A Case Report di Journal of Psychiatry Psychology and Behavioral Research, dukungan support system pada ibu saat hamil dan setelah melahirkan akan mencegah terjadinya baby blues syndrome. Berbagi pengalaman dengan ibu-ibu lain melalui komunitas online ataupun dengan sahabat yang juga seorang ibu, dapat memberikan rasa lega dan mengurangi beban emosional yang ibu rasakan.
          6. Lakukan ‘me time’
          Ketika pikiran dan perasaan sudah mumet, luangkanlah waktu untuk diri sendiri. Mintalah seseorang untuk menjaga bayi supaya kamu bisa keluar rumah. Entah itu mendapatkan sinar matahari, jalan sore, atau pergi ke mall, sekadar untuk ‘me time’ dan menenangkan diri. Menurut jurnal ilmiah berjudul Fenomena Postpartum Blues pada Primipara (Ibu dengan Kelahiran Bayi Pertama), dalam Jurnal Kesehatan Mahardika, menitipkan anak sementara waktu dan melakukan aktivitas yang disenangi dapat mengatasi baby blues syndrome.
          7. Berpikir positif 
          Berpikir positif dapat membantu meningkatkan kesejahteraan emosional ibu. Fokus pada pikiran-pikiran positif dan memahami bahwa gejala syndrome baby blues adalah hal yang wajar dan akan berlalu, dapat membantu ibu menghadapi fase ini dengan lebih baik.
          8. Rutin berolahraga
          Olahraga dapat menjadi salah satu cara ibu mengatasi baby blues syndrome. Dengan rutin beraktivitas fisik emosi ibu bisa terkuras di sana, pun ibu bisa mengalihkan perasaan sedih dan cemas dengan mengolah fisik. Olahraga juga bisa membuat mood menjadi lebih positif.
          9. Konsultasi dengan dokter
          Jika memang sudah tidak bisa ditangani lagi, berkonsultasi dengan dokter adalah pilihan tepat untuk mengatasi baby blues syndrome. Dokter dapat merekomendasikan terapi ataupun pengobatan medis yang tepat, sehingga ibu bisa menjadi lebih sehat dan berpikir positif.
          
        
          `,
          createdAt: new Date(),
          updatedAt: new Date(),
        },
      ],
      {}
    );
  },

  async down(queryInterface) {
    await queryInterface.bulkDelete('Artikels', null, {});
  },
};
