package comp3350.acci.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350.acci.R;
import comp3350.acci.business.AccessCourses;
import comp3350.acci.objects.Course;

public class CoursesActivity extends Activity {

    private AccessCourses accessCourses;
    private List<Course> courseList;
    private ArrayAdapter<Course> courseArrayAdapter;
    private int selectedCoursePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        accessCourses = new AccessCourses();

        try
        {
            courseList = accessCourses.getCourses();
            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courseList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(courseList.get(position).getCourseID());
                    text2.setText(courseList.get(position).getCourseName());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listCourses);
            listView.setAdapter(courseArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button updateButton = (Button)findViewById(R.id.buttonCourseUpdate);
                    Button deleteButton = (Button)findViewById(R.id.buttonCourseDelete);

                    if (position == selectedCoursePosition) {
                        listView.setItemChecked(position, false);
                        updateButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                        selectedCoursePosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        updateButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        selectedCoursePosition = position;
                        selectCourseAtPosition(position);
                    }
                }
            });
            
            final EditText editCourseID = (EditText)findViewById(R.id.editCourseID);
            final Button buttonCourseStudents = (Button)findViewById(R.id.buttonCourseStudents);
            editCourseID.setEnabled(false);
            editCourseID.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {}

				@Override
				public void afterTextChanged(Editable s) {
					buttonCourseStudents.setEnabled(editCourseID.getText().toString().length() > 0);
				}
            });
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_courses, menu);
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

    public void selectCourseAtPosition(int position) {
        Course selected = courseArrayAdapter.getItem(position);

        EditText editID = (EditText)findViewById(R.id.editCourseID);
        EditText editName = (EditText)findViewById(R.id.editCourseName);

        editID.setText(selected.getCourseID());
        editName.setText(selected.getCourseName());
    }

    public void buttonCourseStudentsOnClick(View v) {
        EditText editID = (EditText)findViewById(R.id.editCourseID);
        String courseID = editID.getText().toString();

        Intent csIntent = new Intent(CoursesActivity.this, CourseStudentsActivity.class);
        Bundle b = new Bundle();
        b.putString("courseID", courseID);
        csIntent.putExtras(b);
        CoursesActivity.this.startActivity(csIntent);
    }

    public void buttonCourseCreateOnClick(View v) {
        Course course = createCourseFromEditText();
        String result;

        result = validateCourseData(course, true);
        if (result == null) {
            try {
                course = accessCourses.insertCourse(course);

                courseList = accessCourses.getCourses();
                courseArrayAdapter.notifyDataSetChanged();
                int pos = courseList.indexOf(course);
                if (pos >= 0) {
                    ListView listView = (ListView)findViewById(R.id.listCourses);
                    listView.setSelection(pos);
                }
            } catch (final Exception e) {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
            Messages.warning(this, result);
        }
    }

    public void buttonCourseUpdateOnClick(View v) {
        Course course = createCourseFromEditText();
        String result;

        result = validateCourseData(course, false);
        if (result == null) {
            try {
                course = accessCourses.updateCourse(course);

                courseList = accessCourses.getCourses();
                courseArrayAdapter.notifyDataSetChanged();
                int pos = courseList.indexOf(course);
                if (pos >= 0) {
                    ListView listView = (ListView)findViewById(R.id.listCourses);
                    listView.setSelection(pos);
                }
            } catch (final Exception e) {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
        	Messages.warning(this, result);
        }
    }

    public void buttonCourseDeleteOnClick(View v) {
        Course course = createCourseFromEditText();
        String result;

        try {
            accessCourses.deleteCourse(course);

            int pos = courseList.indexOf(course);
            if (pos >= 0) {
                ListView listView = (ListView) findViewById(R.id.listCourses);
                listView.setSelection(pos);
            }
            courseList = accessCourses.getCourses();
            courseArrayAdapter.notifyDataSetChanged();
        } catch (final Exception e) {
        	Messages.warning(this, e.getMessage());
        }
    }

    private Course createCourseFromEditText() {
        EditText editID = (EditText)findViewById(R.id.editCourseID);
        EditText editName = (EditText)findViewById(R.id.editCourseName);

        Course course = new Course(editID.getText().toString(), editName.getText().toString());

        return course;
    }

    private String validateCourseData(Course course, boolean isNewCourse) {
        if (course.getCourseID().length() == 0) {
            return "Course ID required";
        }

        if (course.getCourseName().length() == 0) {
            return "Course name required";
        }

        if (isNewCourse && accessCourses.getRandom(course.getCourseID()) != null) {
            return "Course ID " + course.getCourseID() + " already exists.";
        }

        return null;
    }
}
