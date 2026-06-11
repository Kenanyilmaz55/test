# CRM API Regression Tests

Bu proje, Yazılım Test Mühendisliği dersi için hazırlanmış, REST-Assured tabanlı bir Otomatik Regresyon Testi projesidir.

## Proje Hakkında
Bu proje, bir CRM uygulamasının backend servislerinin (API) düzgün çalışıp çalışmadığını test eder. Proje içerisinde **Yapay Zeka Destekli Yazılım Test Mühendisliği** pratiklerine uygun olarak Nesne Yönelimli Programlama (POJO) kullanılmış, test verileri ve konfigürasyonlar dışarıya (config.properties) alınmış ve kod tekrarı önlenmiştir.

## Kullanılan Teknolojiler
- **Java 17**
- **Maven**
- **JUnit 5 (Jupiter)**
- **REST-Assured**
- **Jackson**

## Proje Yapısı
```text
src/
 └── test/
      ├── java/com/tanrikulu/crm/
      │    ├── model/
      │    │    └── StateRequest.java        (POST istekleri için POJO)
      │    └── test/
      │         ├── TestBase.java            (Ortak RestAssured ayarları)
      │         └── CrmApiRegressionTest.java (GET ve POST test senaryoları)
      └── resources/
           └── config.properties             (Base URI, Port, DBName ayarları)
```

## Kurulum ve VS Code Üzerinden Çalıştırma

### Gereksinimler
- Sisteminizde **Java 17 (JDK)** kurulu olmalıdır.
- **Visual Studio Code (VS Code)** ve Microsoft'un "Extension Pack for Java" eklentisi kurulu olmalıdır.

### Adım Adım Çalıştırma (VS Code)
1. **VS Code'u Açın:** Sadece `crm-regression-tests` klasörünü VS Code ile açın (File > Open Folder...). (Üstteki "test" klasörünü değil, bizzat crm-regression-tests klasörünü seçin).
2. **Eklentileri Yükleyin:** Eğer VS Code size sağ altta "Extension Pack for Java" yüklemeyi önerirse yükleyin. Yüklü değilse, sol menüdeki Extensions (Eklentiler) sekmesinden (Ctrl+Shift+X) aratıp yükleyebilirsiniz.
3. **Projenin Yüklenmesini Bekleyin:** VS Code alt barda bir yüklenme çubuğu (Importing Maven project) gösterecektir. Bunun bitmesini bekleyin. Bu aşamada IDE, `pom.xml` içerisindeki tüm bağımlılıkları sizin için indirecektir.
4. **Test Dosyasını Açın:** Sol taraftaki Explorer (Gezgin) panelinden `src/test/java/com/tanrikulu/crm/test/CrmApiRegressionTest.java` dosyasını bulun ve tıklayıp açın.
5. **API'nin Çalıştığından Emin Olun:** Kendi API projenizin arka planda çalışır durumda olduğundan emin olun. Gerekirse projede `src/test/resources/config.properties` içerisine girip `base.port` veya `base.uri` alanlarını kendi sunucunuza göre düzenleyebilirsiniz.
6. **Testi Çalıştırın:** Kod dosyasının içinde `public class CrmApiRegressionTest` yazısının veya `@Test` metodlarının hemen üstünde küçük **`▶ Run`** veya **`▶ Run Test`** butonları belirecektir. Bu butona tıklayarak testleri başlatın.
7. **Sonuçları Görüntüleyin:** Ekranın alt kısmında Terminal veya sol sekmedeki **Testing** panelinde (kupa/şişe simgesi) testlerinizin yeşil (başarılı) tik aldığını görebilirsiniz.

## Test Kapsamı
- **GET /BranchList**: Şube listesini getirir. Status Code (200), Response Time (<2000ms), ve dönen JSON verisinin içindeki `status`, `message`, `data` elemanları test edilir.
- **POST /AddNewState**: Yeni bir il ekler. Request body olarak JSON gönderilir. Status Code, Response Time ve yeni eklenen ilin kod/isim bilgileri doğrulanır.
