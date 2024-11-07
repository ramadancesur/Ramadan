import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

class Departman {
    private String ad;
    private Personel takimLideri;
    private List<Personel> personelListesi;
    private List<String> gorevListesi;

    public Departman(String ad, Personel takimLideri) {
        this.ad = ad;
        this.takimLideri = takimLideri;
        this.personelListesi = new ArrayList<>();
        this.gorevListesi = new ArrayList<>();
        this.personelEkle(takimLideri);
    }

    public void takimLideriDegistir(Personel yeniLider) {
        this.takimLideri = yeniLider;
    }

    public void personelEkle(Personel personel) {
        this.personelListesi.add(personel);
    }

    public void personelCikar(Personel personel) {
        this.personelListesi.remove(personel);
    }

    public void gorevEkle(String gorev) {
        this.gorevListesi.add(gorev);
    }

    public void gorevTamamla(String gorev) {
        this.gorevListesi.remove(gorev);
    }
}


abstract class Personel {
    private String ad;
    private String soyad;
    private LocalDate dogumTarihi;

    public Personel(String ad, String soyad, LocalDate dogumTarihi) {
        this.ad = ad;
        this.soyad = soyad;
        this.dogumTarihi = dogumTarihi;
    }

    public abstract int emeklilikYasinaKalanYil();

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public LocalDate getDogumTarihi() {
        return dogumTarihi;
    }
}


class Kadin extends Personel {
    private final int emeklilikYasi = 60;

    public Kadin(String ad, String soyad, LocalDate dogumTarihi) {
        super(ad, soyad, dogumTarihi);
    }

    @Override
    public int emeklilikYasinaKalanYil() {
        int age = Period.between(getDogumTarihi(), LocalDate.now()).getYears();
        return emeklilikYasi - age;
    }
}


class Erkek extends Personel {
    private final int emeklilikYasi = 65;

    public Erkek(String ad, String soyad, LocalDate dogumTarihi) {
        super(ad, soyad, dogumTarihi);
    }

    @Override
    public int emeklilikYasinaKalanYil() {
        int age = Period.between(getDogumTarihi(), LocalDate.now()).getYears();
        return emeklilikYasi - age;
    }
}


public class Main {
    public static void main(String[] args) {
        Kadin kadinPersonel = new Kadin("Ayşe", "Yılmaz", LocalDate.of(1985, 5, 15));
        Erkek erkekPersonel = new Erkek("Mehmet", "Kaya", LocalDate.of(1980, 8, 20));

        System.out.println(kadinPersonel.getAd() + " " + kadinPersonel.getSoyad() + " emekliliğine kalan yıl: " + kadinPersonel.emeklilikYasinaKalanYil());
        System.out.println(erkekPersonel.getAd() + " " + erkekPersonel.getSoyad() + " emekliliğine kalan yıl: " + erkekPersonel.emeklilikYasinaKalanYil());
    }
}
