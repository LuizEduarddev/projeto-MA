import React, {useEffect, useState} from "react";
import { CiShoppingCart as Carrinho} from "react-icons/ci";
import {useNavigate, Link} from 'react-router-dom';
import api from "../../../services/api";

export default function Home()
{
    const list = [

    ]
    const [produtos, setProdutos] = useState([]);
    const [promo, setPromo] = useState([]);
    const [carrinho, setCarrinho] = useState(list);

    function addToCart(id)
    {
        const newCart = [...carrinho, { id }];
        setCarrinho(newCart);
        console.log(carrinho);
    }

    useEffect(() => {
        console.log(carrinho);
    }, [carrinho]);

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

      useEffect(() => {
        api.get('http://localhost:8080/api/produto/get-all')
          .then(response => {
            setProdutos(response.data);
          })
          .catch(error => {
            alert(error);
          });
      }, []);


    return(
        <div>
            <div>
                <h1>
                    Bem-vindo(a), <Link to = '/cliente/perfil'>{localStorage.getItem('username')}</Link>
                </h1>
                <p>ir para o <Link to = '/cliente/carrinho'>carrinho</Link></p>
            </div>

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
            </div>

            <div>
                <h1>Nossos produtos!</h1>
                <ul>
                    {produtos.map(produto => (
                        <li key={produto.idProduto}>
                            <p><strong>{produto.nomeProduto}</strong></p>
                            <p>{produto.descricaoProduto}</p>
                            <p>{produto.precoProduto}</p>
                            <button onClick={() => addToCart(produto.idProduto)}>
                                <Carrinho/>|
                                Adicionar ao carrinho
                            </button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}