import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./pages/ClientSide/LoginPage/Login";
import Promo from "./pages/ClientSide/PromoPage/Promo";
import Home from "./pages/ClientSide/HomePage/Home";
import Profile from "./pages/ClientSide/ProfilePage/Profile";
import Carrinho from "./pages/ClientSide/CarrinhoPage/Carrinho";
import Mesa from "./pages/ClientSide/MesaPage/Mesa";
import Error from "./pages/ClientSide/ErrorPage/Error";
import Pedido from "./pages/ClientSide/PedidosPage/Pedido";
import ItensPedidos from "./pages/ClientSide/PedidosPage/ItensPedidosPage/ItensPedidos";
import PedidosMesa from "./pages/ClientSide/MesaPage/PedidosPorMesa/PedidosMesa";

export default function Rotas(){
    return(
        <Router>
            <Routes> 
                <Route path="/login" Component={Login}/>
                <Route path="/promo" Component={Promo}/>
                <Route path="/home" Component={Home}/>
                <Route path="/cliente/perfil" Component={Profile}/>
                <Route path="/cliente/carrinho" Component={Carrinho}/>
                <Route path="/mesa/:id" Component={Mesa} />
                <Route path="/mesa/pedidos" Component={PedidosMesa} />
                <Route path="/error/page" Component={Error}/>
                <Route path="/cliente/pedidos" Component={Pedido}/>
                <Route path="/cliente/pedidos/itens/:id" Component={ItensPedidos} />
            </Routes>
        </Router>
    );
}