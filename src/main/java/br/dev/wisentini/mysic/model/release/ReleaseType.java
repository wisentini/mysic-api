package br.dev.wisentini.mysic.model.release;

public enum ReleaseType {
    Album  ("Album"),
    Single ("Single"),
    EP     ("EP");

    private final String type;

    ReleaseType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
