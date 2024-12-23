import { useState } from 'react';
import axios from 'axios';

const CustomizePizza = () => {
    const [name, setName] = useState('');
    const [crust, setCrust] = useState('');
    const [sauce, setSauce] = useState('');
    const [toppings, setToppings] = useState<string[]>([]);
    const [price, setPrice] = useState(0);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const crustOptions = ['Thin Crust', 'Thick Crust', 'Stuffed Crust', 'Kurakkan Crust', 'Rice Flour Crust'];
    const sauceOptions = ['Tomato Basil', 'BBQ', 'Spicy Peri Peri', 'Coconut Sambol Sauce', 'Tikka Masala Sauce'];
    const availableToppings = [
        'Pepperoni',
        'Mushrooms',
        'Onions',
        'Sausage',
        'Bacon',
        'Olives',
        'Green Chilies',
        'Pineapple',
        'Paneer',
    ];

    // Handle topping selection
    const handleToppingChange = (topping: string) => {
        setToppings((prev: string[]) => {
            if (prev.includes(topping)) {
                return prev.filter((t) => t !== topping);
            } else {
                return [...prev, topping];
            }
        });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setError('');
        try {
            const response = await axios.post('http://localhost:8080/api/pizzas/customize', {
                name: name || 'Custom Pizza',
                crust,
                sauce,
                toppings,
            });
            setPrice(response.data.price);
        } catch (err) {
            setError('Failed to create pizza. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    const handleReset = () => {
        setName('');
        setCrust('');
        setSauce('');
        setToppings([]);
        setPrice(0);
    };

    return (
        <div className="p-5">
            <h1 className="text-2xl font-bold mb-4">Customize Your Pizza</h1>
            <form onSubmit={handleSubmit} className="space-y-4">
                <input
                    type="text"
                    placeholder="Pizza Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    className="border p-2 w-full"
                />
                <select
                    value={crust}
                    onChange={(e) => setCrust(e.target.value)}
                    className="border p-2 w-full"
                >
                    <option value="">Select Crust</option>
                    {crustOptions.map((crust) => (
                        <option key={crust} value={crust}>
                            {crust}
                        </option>
                    ))}
                </select>
                <select
                    value={sauce}
                    onChange={(e) => setSauce(e.target.value)}
                    className="border p-2 w-full"
                >
                    <option value="">Select Sauce</option>
                    {sauceOptions.map((sauce) => (
                        <option key={sauce} value={sauce}>
                            {sauce}
                        </option>
                    ))}
                </select>
                <div className="space-y-2">
                    <p>Select Toppings:</p>
                    {availableToppings.map((topping) => (
                        <label key={topping} className="block">
                            <input
                                type="checkbox"
                                value={topping}
                                checked={toppings.includes(topping)}
                                onChange={() => handleToppingChange(topping)}
                            />
                            {` ${topping}`}
                        </label>
                    ))}
                </div>
                <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
                    Create Pizza
                </button>
                <button onClick={handleReset} type="button" className="bg-gray-500 text-white px-4 py-2 rounded ml-2">
                    Reset
                </button>
                {loading && <p className="text-blue-500">Creating your pizza...</p>}
                {error && <p className="text-red-500">{error}</p>}
            </form>
            {price > 0 && <p className="mt-4 text-green-500">Total Price: LKR {price}</p>}
        </div>
    );
};

export default CustomizePizza;
