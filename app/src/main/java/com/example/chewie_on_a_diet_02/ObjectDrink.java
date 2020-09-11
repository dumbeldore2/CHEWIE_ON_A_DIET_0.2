package com.example.chewie_on_a_diet_02;

public class ObjectDrink {
    private String naam;
    private String merk;
    private int groote;
    private int calorien;

    public ObjectDrink(String naam, String merk, int groote, int calorien) {
        if (naam.trim().isEmpty() || naam == null)
            throw new IllegalArgumentException("er is een fout met de naam FOODOBJECT");
        if (merk.trim().isEmpty() || merk == null)
            throw new IllegalArgumentException("er is een fout met de merk FOODOBJECT");
        if (groote < 0)
            throw new IllegalArgumentException("er is een fout met de groote FOODOBJECT");
        if (calorien < 0)
            throw new IllegalArgumentException("er is een fout met de calorien FOODOBJECT");

        setNaam(naam);
        setMerk(merk);
        setGroote(groote);
        setCalorien(calorien);
    }
        public String getNaam () {
            return naam;
        }

        public String getMerk () {
            return merk;
        }

        public int getGroote () {
            return groote;
        }

        public int getCalorien () {
            return calorien;
        }

        public void setNaam (String naam){
            this.naam = naam;
        }

        public void setGroote ( int groote){
            this.groote = groote;
        }

        public void setCalorien ( int calorien){
            this.calorien = calorien;
        }

        public void setMerk (String merk){
            this.merk = merk;
        }

        public String info () {
            String uit = "";

            uit += getNaam() + " van het merk " + getMerk() + " met als aantal gram " + getGroote() + "and the ammount of kilos is " + getCalorien();
            return uit;
        }
}
