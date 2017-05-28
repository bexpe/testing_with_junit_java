package src.main.java.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;


class FilePartReaderTest {

	@Test
	@DisplayName("Tests whether calling readLines() before setup() throws a FileNotFoundException")
	public void testReadLinesBeforeSetup() throws FileNotFoundException {
		FilePartReader object = new FilePartReader();

		assertThrows(FileNotFoundException.class, ()->
				object.readLines());
	}

	@Test
	@DisplayName("Tests whether calling setup() with fromLine argument less than (LT) 1 throws a IllegalArgumentException")
	public void testSetupFromLineLT1() {
		FilePartReader object = new FilePartReader();
		assertThrows(IllegalArgumentException.class, ()->
				object.setup("src/main/resources/test_data.txt", 0, 10));
	}

	@Test
	@DisplayName("Tests whether calling setup() with argument toLine less than fromLine throws a IllegalArgumentException")
	public void testSetupToLineLTFromLine() {
		FilePartReader object = new FilePartReader();
		assertThrows(IllegalArgumentException.class, ()->
				object.setup("src/main/resources/test_data.txt", 5, 1));
	}

	@Test
	@DisplayName("Tests whether readLines() called to read first two lines returns expected string")
	public void testReadLines1_2() throws FileNotFoundException {
		FilePartReader object = new FilePartReader();
		object.setup("src/main/resources/test_data.txt", 1, 2);
		assertEquals(object.readLines(), "1a1\n2b 2a\n");
	}

	@Test
	@DisplayName("Tests reading lines from 2 to 4")
	public void testReadLines2_4() throws FileNotFoundException {
		FilePartReader object = new FilePartReader();
		object.setup("src/main/resources/test_data.txt", 2, 4);
		assertEquals(object.readLines(), "2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n");
	}

	@Test
	@DisplayName("Tests reading all lines from file")
	public void testReadLinesAll() throws FileNotFoundException {
		FilePartReader object = new FilePartReader();
		object.setup("src/main/resources/test_data.txt",1, 7);
		assertEquals(object.readLines(), "1a1\n2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n5e 5d 5c 5b 5ax\n" +
				"6f 6ea 6d 6ca 6bb 6a\n7g 7f 7ea\n");
	}

	@Test
	@DisplayName("Test reading when toLine is past End Of File")
	public void testReadLinesPastEof() throws FileNotFoundException {
		FilePartReader object = new FilePartReader();
		object.setup("src/main/resources/test_data.txt",1, 1000);
		assertEquals(object.readLines(), "1a1\n2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n5e 5d 5c 5b 5ax\n" +
				"6f 6ea 6d 6ca 6bb 6a\n7g 7f 7ea\n");
	}
}