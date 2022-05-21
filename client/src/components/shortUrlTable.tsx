import React from "react";
import Link from "src/entities/link";

const ShortUrlTable = ({ links }: { links: Link[] }) => {
  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Code</th>
            <th>Short Url</th>
            <th>Original Url</th>
            <th>Created Date Time</th>
          </tr>
        </thead>
        <tbody>
          {links.map((x) => (
            <tr>
              <td>{x.code}</td>
              <td>{x.shortUrl}</td>
              <td>{x.originalUrl}</td>
              <td>{x.createdDateTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ShortUrlTable;
