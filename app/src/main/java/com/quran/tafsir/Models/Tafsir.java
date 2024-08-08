package com.quran.tafsir.Models;

import java.util.List;

public class Tafsir {
    private String name;
    private List<Soar> soar;

    public Tafsir(String name, List<Soar> soar) {
        this.name = name;
        this.soar = soar;
    }

    public String getName() { return name; }
    public List<Soar> getSoar() { return soar; }

    public static class Soar {
        private int id;
        private int sura_id;
        private int tafsir_id;
        private String url;
        private String name;

        public Soar(int id, int sura_id, int tafsir_id, String url, String name) {
            this.id = id;
            this.sura_id = sura_id;
            this.tafsir_id = tafsir_id;
            this.url = url;
            this.name = name;
        }

        public int getId() { return id; }
        public int getSuraId() { return sura_id; }
        public int getTafsirId() { return tafsir_id; }
        public String getUrl() { return url; }
        public String getName() { return name; }
    }
}
