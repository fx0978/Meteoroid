package states;

public enum GameState {
    PLAY, MENU, GAMEOVER, PAUSED, INSTRUCTIONS;

    private static GameState state = MENU;

    protected static void setGameStatePlay() {
        state = PLAY;
    }

    protected static void setGameStateMenu() {
        state = MENU;
    }

    protected static void setGameStateGameOver() {
        state = GAMEOVER;
    }

    protected static void setGameStatePaused() {
        state = PAUSED;
    }

    protected static void setGameStateInstruction() {
        state = INSTRUCTIONS;
    }

    public static GameState getGameState() {
        return state;
    }
}
