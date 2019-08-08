import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

class Converter {
    public static void main(String[] args) {
        String XSLFILE = args[0];
        String INFILE = args[1];
        String OUTFILE = args[2];

        StreamSource xslcode =
				new StreamSource(new File(XSLFILE));
		StreamSource input =
				new StreamSource(new File(INFILE));
		StreamResult output =
				new StreamResult(new File(OUTFILE));

		try {
			TransformerFactory tf =
					TransformerFactory
					.newInstance();

			Transformer trans =
					tf.newTransformer(xslcode);

			trans.transform(input, output);

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}