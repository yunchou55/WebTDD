/**
 * 
 */
package com.example.ou.webtdd;



import android.test.AndroidTestCase;

/**
 * @author ly
 *
 */
public class EditNumberTest extends AndroidTestCase {
	EditNumber mEditNumber;

	public EditNumberTest(){
		this("EditNumberTest");
	}
	/**
	 * @param name
	 */
	public EditNumberTest(String name) {
		setName(name);
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		mEditNumber=new EditNumber(mContext);//mContext->AndroidTestCase
		mEditNumber.setFocusable(true);
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}


	/**
	 * Test method for {@link EditNumber#clear()}.
	 */
	public void testClear() {
		final String value="123.45";
		mEditNumber.setText(value);
		mEditNumber.clear();
		assertEquals("", mEditNumber.getText().toString());
	}

	/**
	 * Test method for {@link EditNumber#setNumber(double)}.
	 */
	public void testSetNumber() {
		mEditNumber.setNumber(123.45);

		assertEquals("123.45", mEditNumber.getText().toString());
	}

	/**
	 * Test method for {@link EditNumber#getNumber()}.
	 */
	public void testGetNumber() {
		//fail("Not yet implemented");
		mEditNumber.setNumber(123.45);
		assertEquals(123.45, mEditNumber.getNumber());
	}

}
