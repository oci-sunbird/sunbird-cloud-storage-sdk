package org.sunbird.cloud.storage.service

import org.scalatest.FlatSpec
import org.scalatest._
import org.sunbird.cloud.storage.conf.AppConf
import org.sunbird.cloud.storage.exception.StorageServiceException
import org.sunbird.cloud.storage.factory.{StorageConfig, StorageServiceFactory}

class TestOCIS3StorageService extends FlatSpec with Matchers {

    it should "test for oci s3 storage" in {

        // val s3Service = StorageServiceFactory.getStorageService(StorageConfig("oci", AppConf.getStorageKey("oci"), AppConf.getStorageSecret("oci"),AppConf.getStorageEndpoint("oci")))
        val s3Service = StorageServiceFactory.getStorageService(StorageConfig("oci", AppConf.getStorageKey("oci"), AppConf.getStorageSecret("oci"),Some("apaccpt03.compat.objectstorage.ap-hyderabad-1.oraclecloud.com")))

        val storageContainer = AppConf.getConfig("oci_storage_container")


        val caught =
            intercept[StorageServiceException]{
                s3Service.upload(storageContainer, "src/test/resources/1234/test-blob.log", "testUpload/1234/", Option(false),Option(5), Option(2), None)
            }
        assert(caught.getMessage.contains("Failed to upload."))
        s3Service.closeContext()
    }
}
