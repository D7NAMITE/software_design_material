public class AdapterUnit implements IUnit{
    private LegacyUnit legacyUnit;
    private String name;
    private Integer hp;

    public AdapterUnit(LegacyUnit legacyUnit) {
        this.legacyUnit = legacyUnit;
        this.name = "Legacy";
        this.hp = 999;
    }

    @Override
    public void move() {
        legacyUnit.walk();
        int x = legacyUnit.getPositionX();
        int y = legacyUnit.getPositionY();
        if (x <= 0 || x >= Game.SIZE) {
            hp--;
        }
        if (y <= 0 || y >= Game.SIZE) {
            hp--;
        }
    }

    @Override
    public int getX() {
        return legacyUnit.getPositionX();
    }

    @Override
    public int getY() {
        return legacyUnit.getPositionY();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHealth() {
        return this.hp;
    }

    @Override
    public boolean dead() {
        return this.hp <= 0;
    }
}
