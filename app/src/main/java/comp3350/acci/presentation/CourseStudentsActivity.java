package comp3350.acci.presentation;

import java.util.ArrayList;

import comp3350.acci.R;
import comp3350.acci.R.id;
import comp3350.acci.R.layout;
import comp3350.acci.business.AccessSC;
import comp3350.acci.objects.SC;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CourseStudentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_course_students);
		
		Bundle b = getIntent().getExtras();
		String courseID = b.getString("courseID");
		
		final ArrayList<SC> registrations = new ArrayList<SC>();
		AccessSC accessSC = new AccessSC();
		
		SC sc = accessSC.getCS(courseID);
		while (sc != null) {
			registrations.add(sc);
			sc = accessSC.getCS(courseID);
		}
		
	    final ArrayAdapter<SC> csArrayAdapter = new ArrayAdapter<SC>(this, android.R.layout.simple_list_item_2, android.R.id.text1, registrations)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(registrations.get(position).getStudentID() + ": " + registrations.get(position).getStudentName());
                text2.setText(registrations.get(position).getGrade());

                return view;
            }
        };

        ListView listView = (ListView)findViewById(id.listCourseStudents);
        listView.setAdapter(csArrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_course_students, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}
}
