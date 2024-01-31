import React from "react";
import './styles.css';

export default function Login(){
    return (
        <div className="login-container">
            <section className="form">
                <form>
                    <h1>Acesse sua conta!</h1>
                    <input placeholder="Nome ou e-mail de usuário"/>
                    <input type="password" placeholder="Senha de usuário"/>
                    <button type="submit" className="button">Login</button>
                </form>
            </section>
        </div>
    );
}