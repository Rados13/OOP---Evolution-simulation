package logic;

enum MapDirection{
    NORTH, SOUTH, WEST, EAST,NORTHEAST,NORTHWEST,SOUTHEAST,SOUTHWEST;

    @Override
    public String toString(){
        switch (this){
            case EAST:return "Wschod";
            case SOUTH:return "Poludnie";
            case WEST:return "Zachod";
            case NORTH:return "Polnoc";
            case NORTHEAST: return "Polnocny-wschod";
            case NORTHWEST: return "Polnocny-zachod";
            case SOUTHEAST: return "Poludniowy-wschod";
            case SOUTHWEST: return "Poludniowy-zachod";
        }
        return null;
    }
    public MapDirection next(){
        switch (this){
            case EAST:return SOUTHEAST;
            case SOUTHEAST:return SOUTH;
            case SOUTH:return SOUTHWEST;
            case SOUTHWEST:return WEST;
            case WEST:return NORTHWEST;
            case NORTHWEST:return NORTH;
            case NORTH:return NORTHEAST;
            case NORTHEAST:return EAST;
        }
        return null;
    }
    public MapDirection previous(){
        switch (this){
            case EAST:return NORTHEAST;
            case SOUTHEAST:return EAST;
            case SOUTH:return SOUTHEAST;
            case SOUTHWEST:return SOUTH;
            case WEST:return SOUTHWEST;
            case NORTHWEST:return WEST;
            case NORTH:return NORTHWEST;
            case NORTHEAST:return NORTH;

        }
        return null;
    }
    public Vector2d toUnitVector(){
        switch(this){
            case NORTH:return new Vector2d(0,1);
            case NORTHEAST: return new Vector2d(1,1);
            case EAST:return new Vector2d(1,0);
            case SOUTHEAST:return new Vector2d(1,-1);
            case SOUTH:return new Vector2d(0,-1);
            case SOUTHWEST:return new Vector2d(-1,-1);
            case WEST:return new Vector2d(-1,0);
            case NORTHWEST:return new Vector2d(-1,1);
        }
        return null;
    }

}