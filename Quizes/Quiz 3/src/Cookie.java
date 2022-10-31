public class Cookie extends Food {

    private boolean tasty;
    private int size;

    public Cookie(boolean tasty, int size){
        super(tasty);
        this.size = size;
    }
    @Override
    public boolean isTasty() {
        return super.isTasty();
    }

    public boolean isBig(){
        if (this.size > 10){
            return true;
        }
        return false;
    }
}
