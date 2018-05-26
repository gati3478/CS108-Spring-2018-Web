package servlet;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NumberPowerServletTest {

    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private StringWriter stringWriter;

    @Before
    public void initMocks() throws IOException {
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);

        stringWriter = new StringWriter();
        when(responseMock.getWriter()).thenReturn(new PrintWriter(stringWriter));
    }

    @Test
    public void testGetWithPositivePow1() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("4");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("3");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("64.0", stringWriter.getBuffer().toString());

        // Some other methods
        verify(responseMock).getWriter();
        verify(requestMock, times(2)).getParameter(anyString());
    }

    @Test
    public void testGetWithPositivePow2() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("-3");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("4");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("81.0", stringWriter.toString());
    }

    @Test
    public void testGetWithPositivePow3() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("-5");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("3");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("-125.0", stringWriter.toString());
    }

    @Test
    public void testGetWithNegativePow1() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("4");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("-2");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("0.0625", stringWriter.toString());
    }

    @Test
    public void testGetWithNegativePow2() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("8");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("-1");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("0.125", stringWriter.toString());
    }

    @Test
    public void testGetWithNegativePow3() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("-10");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("-1");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("-0.1", stringWriter.toString());
    }

    @Test
    public void testGetWithNegativePow4() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("-5");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("-3");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("-0.008", stringWriter.toString());
    }

    @Test
    public void testGetWithFractionPow1() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("9");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("0.5");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("3.0", stringWriter.toString());
    }

    @Test
    public void testGetWithFractionPow3() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("32");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("0.2");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("2.0", stringWriter.toString());
    }

    @Test
    public void testGetWithFractionPow4() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("-32");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("0.2");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("NaN", stringWriter.toString());
    }

    @Test
    public void testGetWithBadNumber() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("?#");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("0.2");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("NaN", stringWriter.toString());
    }

    @Test
    public void testGetWithBadPower() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("5.5");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn("FPA");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("NaN", stringWriter.toString());
    }

    @Test
    public void testGetWithBadInputs() throws IOException, ServletException {
        when(requestMock.getParameter(NumberPowerServlet.NUMBER)).thenReturn("/n/n/t");
        when(requestMock.getParameter(NumberPowerServlet.POWER)).thenReturn(" []");

        new NumberPowerServlet().doGet(requestMock, responseMock);

        assertEquals("NaN", stringWriter.toString());
    }

}
