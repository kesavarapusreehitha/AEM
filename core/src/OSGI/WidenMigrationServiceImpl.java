package com.migration.core.services.impl;

import com.migration.core.services.WidenMigrationService;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.io.InputStream;

@Component(service = WidenMigrationService.class)
public class WidenMigrationServiceImpl implements WidenMigrationService  {

    private static final Logger LOG = LoggerFactory.getLogger(WidenMigrationServiceImpl.class);

    private static final String API_URL =
            "https://api.widencollective.com/v2/assets/search?limit=100";
    private static final String TOKEN = "API-Key 1e5eada04a7e1dbe6990d843e1ad4c6a";

    @Override
    public String migrateAssets(ResourceResolver resolver) {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            // 1. Call Widen API
            HttpGet request = new HttpGet(API_URL);
            request.setHeader("Authorization", TOKEN);

            CloseableHttpResponse response = client.execute(request);
            String json = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

            JSONObject obj = new JSONObject(json);
            JSONArray assets = obj.getJSONArray("data");

            AssetManager assetManager = resolver.adaptTo(AssetManager.class);
            String folderPath = "/content/dam/migrated";
            createFolderIfNotExists(resolver, folderPath);

            // 2. Loop through assets
            for (int i = 0; i < assets.length(); i++) {

                JSONObject asset = assets.getJSONObject(i);

                String id = asset.getString("id");
                String filename = asset.getString("filename");
                String downloadUrl = asset.getString("download_url");
                String mimeType = asset.optString("mime_type", "application/octet-stream");

                LOG.info("Migrating Widen asset -> ID: {}, file: {}", id, filename);

                // 3. Download binary
                HttpGet downloadReq = new HttpGet(downloadUrl);
                downloadReq.setHeader("Authorization", TOKEN);

                CloseableHttpResponse downloadResp = client.execute(downloadReq);
                InputStream binary = downloadResp.getEntity().getContent();

                String damPath = folderPath + "/" + filename;

                // 4. Upload into AEM DAM
                Asset damAsset = assetManager.createAsset(
                        damPath,
                        binary,
                        mimeType,
                        true
                );

                // 5. Save metadata
                Node metadata = damAsset.getMetadata();
            }
        }
    }
}            
