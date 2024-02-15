import React, {useEffect, useState} from "react";
import {Link} from 'react-router-dom';
import api from "../../../services/api";

export default function Promo()
{
    const [produtos, setProdutos] = useState([]);
    const [promo, setPromo] = useState([]);

    useEffect(() => {
        api.get('http://localhost:8080/api/produto/get-all')
          .then(response => {
            setProdutos(response.data);
            const newPromo = response.data
                            .filter(produto => produto.promoProduto === 1);
            setPromo(newPromo);
          })
          .catch(error => {
            alert(error);
          });
      }, []);


    return(
        <div>
            <h1>Bem-vindo(a), <strong>{localStorage.getItem('username')}!</strong></h1>
            
            <div>
                <h1><strong>PROMOÇOES DO DIA!</strong></h1>
                <ul>
                    {promo.map(promocao => (
                        <li key={promocao.idProduto}>
                            <p><strong>{promocao.nomeProduto}</strong></p>
                            <p>{promocao.descricaoProduto}</p>
                            <p>{promocao.precoProduto}</p>
                        </li>
                    ))}
                </ul>
                <Link to = '/home'>ir para a página inicial.</Link>
            </div>
        </div>
    );
}