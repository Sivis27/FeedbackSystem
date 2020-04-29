import React from "react";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import RouterComponent from "./router/router";

function App() {  
  return (
    <MuiThemeProvider>
      <div className="App">
        <RouterComponent />
      </div>
    </MuiThemeProvider>
  );
}

export default App;
