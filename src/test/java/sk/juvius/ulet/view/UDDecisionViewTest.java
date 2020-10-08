package sk.juvius.ulet.view;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sk.juvius.ulet.ui.UIUtils;

class UDDecisionViewTest {

    @Disabled
    @Test
    void testView() {
        UDDecisionView view = new UDDecisionView(true);
        UIUtils.moveToMousePointer(view);
        view.setVisible(true);
    }
}
