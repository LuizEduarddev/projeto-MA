import React from "react";
import './styles.css';
import AIMage from '../../assets/createNewPerson/IAPhoto.jpg'
import {Link} from 'react-router-dom'
import { FiArrowLeft } from "react-icons/fi";

export default function NewPerson(){
    return (
        <div className="new-person-container">
            <div className="content">
                <section className="form">
                    <img src={AIMage} alt="AIMage"/>
                    <h1>Adicione um novo usuário</h1>
                    <p>
                        Adicione as informaçoes necessárias para o usuário
                        e em seguida clique no botao 'Adicionar'.
                    </p>
                    <Link className="back-link" to="/registro">
                        <FiArrowLeft size={18} color="251fc5"/>
                        Voltar para página inicial
                    </Link>
                </section>

                <form>
                    <input placeholder="Nome"></input>
                    <input placeholder="Cpf"></input>
                    <input type="date"></input>
                    <input placeholder="Fortuna bruta"></input>

                    <button className="button" type="submit">
                        Adicionar usuário</button>
                </form>
            </div>
        </div>
    );
}