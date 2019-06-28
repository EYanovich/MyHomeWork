public enum Person {

    firstName( "Иван" ),
    lastName( "Иванов" ),
    email( "ivan@mail.ru" ),
    phone( "292112233" ),
    cardName( "Ivan" ),
    cardNum( "4242 4242 4242 4242" ),
    cardCVC( "123" );


    private String value;

    Person(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
