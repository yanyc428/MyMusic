package enumItem;

public enum Area {
    UNK(0),
    CHN_M(11), CHN_F(12), CHN_G(13),
    AME_M(21), AME_F(22), AME_G(23),
    JPN_M(31), JPN_F(32), JPN_G(33),
    KOR_M(41), KOR_F(42), KOR_G(43),
    OTH_M(51), OTH_F(52), OTH_G(53);


    private Integer name;

    Area(Integer name) {
        this.name = name;
    }

    public Integer number(){
        return this.name;
    }
}
