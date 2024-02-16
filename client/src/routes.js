import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./pages/ClientSide/LoginPage/Login";
import Promo from "./pages/ClientSide/PromoPage/Promo";
import Home from "./pages/ClientSide/HomePage/Home";
import Profile from "./pages/ClientSide/ProfilePage/Profile";
import Carrinho from "./pages/ClientSide/CarrinhoPage/Carrinho";

export default function Rotas(){
    return(
        <Router>
            <Routes> 
                <Route path="/login" Component={Login}/>
                <Route path="/promo" Component={Promo}/>
                <Route path="/home" Component={Home}/>
                <Route path="/cliente/perfil" Component={Profile}/>
                <Route path="/cliente/carrinho" Component={Carrinho}/>
            </Routes>
        </Router>
    );
}