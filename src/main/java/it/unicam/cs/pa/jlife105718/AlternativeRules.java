package it.unicam.cs.pa.jlife105718;

public class AlternativeRules implements Regole<Cellula>{
    private Campo<?> campo;

    public AlternativeRules(Campo<?> campo) {
        this.campo= campo;
    }

    @Override
    public Cellula step(Cellula cellula) {
        return null;
    }
}
