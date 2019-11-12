package ta2;

import java.util.Arrays;

public class Darbuotojas {
    private int id;
    private String vardas, pavarde;
    private boolean[] darboDienos = new boolean[7];

    public boolean[] getDarboDienos() {
        return darboDienos;
    }

    public void setDarboDienos(boolean[] darboDienos) {
        this.darboDienos = darboDienos;
    }

    @Override
    public String toString() {
        return "Darbuotojas{" +
                "id=" + id +
                ", vardas='" + vardas + '\'' +
                ", pavarde='" + pavarde + '\'' +
                ", darboDienos=" + Arrays.toString(darboDienos) +
                '}';
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =  id;
    }

    public String getPirm() {
        return darboDienos[0] ? "T":"N";
    }
}
