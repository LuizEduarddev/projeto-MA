import React, {useEffect, useState} from "react";
import { CiShoppingCart as Carrinho} from "react-icons/ci";
import {useNavigate, Link} from 'react-router-dom';
import api from "../../../services/api";

export default function Home()
{
    const [produtos, setProdutos] = useState([]);
    const [promo, setPromo] = useState([]);
    const [carrinho, setCarrinho] = useState([]);

    const [quantidade, setQuantidade] = useState(1);
    const increcrementQuantidade = () => setQuantidade(quantidade + 1);
    let decrementQuantidade = () => setQuantidade(quantidade - 1);
    if (quantidade <=1){
        decrementQuantidade = () => setQuantidade(1);
    }


    function addToCart(produto, quantidade) {

        let existCart = localStorage.getItem('cart')
        if (existCart)
        {
            let cartItens = JSON.parse(existCart);
            let produtoExistente = cartItens.find(item => item.idProduto === produto.idProduto);
        
            if (produtoExistente) {
                if (produtoExistente.quantidade !== quantidade) {
                    produtoExistente.quantidade = quantidade;
                    localStorage.setItem('cart', JSON.stringify(cartItens));
                    setCarrinho([...cartItens]);
                }
            } else {
                cartItens.push({
                    idProduto: produto.idProduto,
                    quantidade: quantidade
                });
                localStorage.setItem('cart', JSON.stringify(cartItens));
                setCarrinho([...cartItens]);
            }
        }
        else
        {
            const cartItens = [{
                idProduto: produto.idProduto,
                quantidade: quantidade
            }];
            localStorage.setItem('cart', JSON.stringify(cartItens));
            setCarrinho([...cartItens]);
        }
    }
    

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
                            <button onClick={() => addToCart(produto, quantidade)}>
                                <Carrinho/>|
                                Adicionar ao carrinho
                            </button>
                            <div>
                                <button onClick={decrementQuantidade}>
                                    -
                                </button>
                                <p>{quantidade}</p>
                                <button onClick={increcrementQuantidade }>
                                    +
                                </button>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}