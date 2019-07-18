public enum DbEnumUsers {
    id( 1 ),
    userName( 2 ),
    login( 3 ),
    password( 4 ),
    email( 5 ),
    send_to( 6 ),
    themeText( 7 ),
    mailText( 8 );


    private int columId;

    DbEnumUsers(int columId) {
        this.columId = columId;
    }

    public int getValue() {
        return columId;
    }
}
