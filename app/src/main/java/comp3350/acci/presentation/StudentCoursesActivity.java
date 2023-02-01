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

public class StudentCoursesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_student_courses);

		
		Bundle b = getIntent().getExtras();
		String studentID = b.getString("studentID");
		
		final ArrayList<SC> registrations = new ArrayList<SC>();
		AccessSC accessSC = new AccessSC();
		
		SC sc = accessSC.getSC(studentID);
		while (sc != null) {
			registrations.add(sc);
			sc = accessSC.getSC(studentID);
		}
		
	    final ArrayAdapter<SC> scArrayAdapter = new ArrayAdapter<SC>(this, android.R.layout.simple_list_item_2, android.R.id.text1, registrations)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(registrations.get(position).getCourseID() + ": " + registrations.get(position).getCourseName());
                text2.setText(registrations.get(position).getGrade());

                return view;
            }
        };

        ListView listView = (ListView)findViewById(id.listStudentCourses);
        listView.setAdapter(scArrayAdapter);

        TextView viewGPA = (TextView)findViewById(id.viewGPA);
        viewGPA.setText(accessSC.getGPA());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_student_courses, menu);
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
