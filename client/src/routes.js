import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./pages/login";
import Person from "./pages/person";
import NewPerson from "./pages/newPerson";

export default function Rotas(){
    return(
        <Router>
            <Routes> 
                <Route path="/login" Component={Login}/>
                <Route path="/registro" Component={Person}/>
                <Route path="/adicionar-usuario" Component={NewPerson}/>
            </Routes>
        </Router>
    );
}