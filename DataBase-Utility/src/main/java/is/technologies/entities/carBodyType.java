package is.technologies.entities;

public enum carBodyType {
    sedan("sedan"),
    hatch("hatch"),
    universal("universal"),
    coupe("coupe"),
    pickup("pickup"),
    rodster("rodster");
    private final String CBType;
    carBodyType(String _CBType)
    {
        this.CBType = _CBType;
    }
    public String getCBType()
    {
        return CBType;
    }
    public static carBodyType getByString(String _CBType)
    {
        for(carBodyType cbt : values())
        {
            if(cbt.getCBType().equals(_CBType))
            {
                return cbt;
            }
        }
        throw new IllegalArgumentException("No founded types");
    }
    public String toString()
    {
        return CBType;
    }
}
