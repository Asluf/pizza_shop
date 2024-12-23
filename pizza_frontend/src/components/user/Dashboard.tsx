import React, { useEffect, useState } from 'react';
import Navbar from '../common/NavBar';
import { useNavigate } from 'react-router-dom';
import { useTokenContext } from '../../contexts/TokenContext';
import axios from 'axios';
import { Pizza } from '../../models/pizza';
import pizza2 from '../../assets/pizza2.png';
import Swal from 'sweetalert2';
// import alert from '../assets/alert.wav';

const Dashboard: React.FC = () => {
  // const notificationSound = new Audio(alert);
  const navigate = useNavigate();
  const { userEmail } = useTokenContext();

  const [pizzas, setPizzas] = useState<Pizza[]>([]);

  const [cart, setCart] = useState<any[]>(() => {
    const savedCart = localStorage.getItem('cart');
    return savedCart ? JSON.parse(savedCart) : [];
  });

  useEffect(() => {
    if (!userEmail || userEmail === '') {
      navigate('/login');
    } else {
      fetchPizzas();
    }
  }, []);

  const fetchPizzas = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/pizzas/all');
      setPizzas(response.data);
    } catch (error) {
      console.error('Error fetching pizzas:', error);
    }
  };

  const addToCart = async (pizza: Pizza) => {
    const cartItem = {
      userEmail: localStorage.getItem('userEmail'),
      pizzaName: pizza.name || 'Custom Pizza',
      crust: pizza.crust,
      sauce: pizza.sauce,
      cheese: pizza.cheese,
      // toppings: toppings.join(', '),
      toppings: pizza.toppings,
      // price: calculatePrice(),
      price: pizza.price,
      totalPrice: pizza.price,
      quantity: 1,
    };

    try {
      const response = await axios.post('http://localhost:8080/api/cart/add', cartItem);
      if (response.data) {
        Swal.fire('Success', 'Pizza added to cart!', 'success');
      } else {
        Swal.fire('Error', 'Failed to add pizza to cart.', 'error');
      }
    } catch (error) {
      console.error('Error adding to cart:', error);
    }
  };


  return (
    <div className="w-[100%] h-[100vh]">
      <Navbar />
      {/* <div className="w-[100%] flex justify-center items-center">
        <div className="mt-5 bg-brown-300 bg-opacity-50 w-[450px] h-[100px] rounded-xl px-10 py-5 shadow-xl flex flex-col gap-4 ">

        </div>
      </div> */}
      {/* <div className='w-[100%] flex justify-center items-center'>
        <div className="mt-5 w-[60vw] min-h-[30vh] rounded-xl px-10 py-5 shadow-xl flex flex-col gap-4">
         
        </div>
      </div> */}

      <h3 className='pt-4 px-4 text-2xl font-bold text-gray-800'>Featured</h3>

      <div className="w-full p-5 grid grid-cols-4 gap-4">
        {pizzas.map((pizza: Pizza) => (
          <div key={pizza.name} className="border border-brown-800 rounded-lg shadow-lg p-5">
            <img src={pizza2} alt="Pizza" className="w-full h-40 object-contain rounded-md mb-4" />
            <h2 className="text-lg font-bold">{pizza.name}</h2>
            <p>Crust: {pizza.crust}</p>
            <p>Sauce: {pizza.sauce}</p>
            <p>Cheese: {pizza.cheese}</p>
            <p>Toppings: {pizza.toppings ? pizza.toppings.split(',').join(', ') : 'None'}</p>
            <p className="text-green-600 font-bold">Price: LKR {pizza.price}</p>
            <button
              onClick={() => addToCart(pizza)}
              className="bg-yellow-700 hover:bg-yellow-500 text-white px-4 py-2 rounded mt-4"
            >
              Add to Cart
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;
