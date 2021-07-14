package codeanalysis;

public class AbsAndInsPair {
    private Double instability;
    private Double abstractness;


    AbsAndInsPair(Double abstractness, Double instability){
        this.instability = instability;
        this.abstractness = abstractness;

    }

    @Override
    public String toString(){
        return "I"+String.format("%.2f",instability) + ": A" +  String.format("%.2f", abstractness);
    }

    public Double getInstability() {
        return instability;
    }

    public void setInstability(Double instability) {
        this.instability = instability;
    }

    public Double getAbstractness() {
        return abstractness;
    }

    public void setAbstractness(Double abstractness) {
        this.abstractness = abstractness;
    }
}
