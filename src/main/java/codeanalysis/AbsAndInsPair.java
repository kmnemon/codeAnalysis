package codeanalysis;

public class AbsAndInsPair {
    private Double abstractness;
    private Double instability;

    AbsAndInsPair(Double abstractness, Double instability){
        this.abstractness = abstractness;
        this.instability = instability;
    }

    @Override
    public String toString(){
        return abstractness.toString() + ":" + instability.toString();
    }

    public Double getAbstractness() {
        return abstractness;
    }

    public void setAbstractness(Double abstractness) {
        this.abstractness = abstractness;
    }

    public Double getInstability() {
        return instability;
    }

    public void setInstability(Double instability) {
        this.instability = instability;
    }
}
