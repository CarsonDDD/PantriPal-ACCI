package comp3350.acci.presentation.fragments;

public interface ACCIFragmentable {

    default boolean hasNavigationBar() {
        return true;
    }

    default boolean hasBackButton() {
        return false;
    }
}
