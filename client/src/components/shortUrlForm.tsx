import React, { Dispatch, SetStateAction } from "react";
import axios from "axios";
import Link from "src/entities/link";

const ShortUrlForm = ({ links, setLinks }: { links: Link[]; setLinks: Dispatch<SetStateAction<Link[]>> }) => {

  const [url, setUrl] = React.useState("");

  const handleUrlChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUrl(e.target.value);
  };

  const handler = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    let requestBody = { url: url };

    const { data, status } = await axios.post<Link>("http://www.surl.io/api/links", requestBody, {
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    });

    if (status == 201) {
      let newLinks = [...links];
      newLinks.push(data);
      setLinks(newLinks);
      setUrl("");
    }
  };

  return (
    <>
      <div>
        <form>
          <label htmlFor="url">Url: </label>
          <input type="text" name="url" placeholder="url" value={url} onChange={handleUrlChange} />
          <button type="button" name="submit" onClick={handler}>
            Submit
          </button>
        </form>
      </div>
    </>
  );
};

export default ShortUrlForm;
