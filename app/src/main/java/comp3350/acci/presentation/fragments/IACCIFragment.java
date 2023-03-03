package comp3350.acci.presentation.fragments;

public interface IACCIFragment {

    default boolean hasNavigationBar() {
        return true;
    }

    default boolean hasBackButton() {
        return false;
    }
}
