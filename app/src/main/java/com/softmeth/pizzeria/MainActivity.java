//package com.softmeth.pizzeria;
//
//import android.os.Bundle;
//
//import com.google.android.material.snackbar.Snackbar;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.view.View;
//
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//
//
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//public class MainActivity extends AppCompatActivity {
//
//    private AppBarConfiguration appBarConfiguration;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_view);
////
////        setSupportActionBar(binding.toolbar);
////
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
////        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
////        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
////
////        binding.fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//    }
//
//    /**
//     * Event Handler to open Chicago Order Screen
//     */
//    public void orderChicagoPizza(View v){
//        Toast.makeText(MainActivity.this, "TESTINGasdfasdfasdf", Toast.LENGTH_LONG).show();
////        try {
////            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("ChicagoView.fxml"));
////            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
////            Stage stage = new Stage();
////            stage.setTitle("Order Chicago Style Pizza");
////            stage.setResizable(false);
////            stage.setScene(scene);
////            stage.show();
////        }catch(Exception ignored){
////        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
////    @Override
////    public boolean onSupportNavigateUp() {
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
////        return NavigationUI.navigateUp(navController, appBarConfiguration)
////                || super.onSupportNavigateUp();
////    }
//}