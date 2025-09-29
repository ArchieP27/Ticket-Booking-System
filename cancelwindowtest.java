import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CancelWindowTest {

    private cancelWindow window;

    @BeforeEach

    void setUp() {
        window = new cancelWindow("Archita");
    }

    @Test
    void testCancellationRequestAlreadyGenerated() {
        window.getFlightComboBox().setSelectedItem("AI257");
        window.getPassengerComboBox().setSelectedItem("C0037");
        window.getConfirmButton().doClick();
        assertEquals("A cancellation request has already been generated for this flight and passenger.", window.getErrorMessage());
    }

    @Test

    void testCannotCancelFlightAlreadyDeparted() {
        window.getFlightComboBox().setSelectedItem("AI100");
        window.getConfirmButton().doClick();
        assertEquals("Cannot cancel a flight that has already departed or is departing today.", window.getErrorMessage());
    }

    @Test
    void testSuccessfulCancellationRequest() {
        window.getFlightComboBox().setSelectedItem("AI259");
        window.getPassengerComboBox().setSelectedItem("C0038");
        window.getConfirmButton().doClick();
        assertNull(window.getErrorMessage());
        assertTrue(window.isDisposed());
    }

}
