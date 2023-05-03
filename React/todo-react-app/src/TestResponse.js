import React from 'react';
import { exampleFunction } from './Test';

function TestResponse() {
    const [data, setData] = React.useState(null);

    React.useEffect(() => {
        exampleFunction()
            .then((response) => setData(response))
            .catch((error) => console.error(error));
    }, []);

    return (
        <div>
            {data ? <div>{data}</div> : <div>Loading...</div>}
        </div>
    );
}
export default TestResponse;